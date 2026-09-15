package kcart.daoimpl;

import kcart.dao.UserDAO;
import kcart.model.User;
import java.sql.*;
import kcart.util.DBConnection;
import kcart.util.Message;

public class UserDAOImpl implements UserDAO {
    private static final String TABLE_NAME = "tbl_user";
    private static final String COL_USER_ID = "user_id";
    private static final String COL_FIRST_NAME = "first_name";
    private static final String COL_MIDDLE_NAME = "middle_name";
    private static final String COL_LAST_NAME = "last_name";
    private static final String COL_CONTACT_NO = "contact_no";
    private static final String COL_ROLE = "role";
    private static final String COL_USERNAME = "username";
    private static final String COL_PASSWORD = "password";
    private static final String COL_USER_STATUS = "user_status";
    private static final String COL_CREATED_AT = "created_at";

    private Connection conn;
     public UserDAOImpl() {
        this.conn = DBConnection.getConnection(); // Establish connection.
    }

    @Override
    public User findByUsername(String username) {
       User user = null;
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_USERNAME + " = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new User(
                    rs.getInt(COL_USER_ID),
                    rs.getString(COL_FIRST_NAME),
                    rs.getString(COL_MIDDLE_NAME),
                    rs.getString(COL_LAST_NAME),
                    rs.getString(COL_CONTACT_NO),
                    rs.getString(COL_ROLE),
                    rs.getString(COL_USERNAME),
                    rs.getString(COL_PASSWORD),
                    rs.getString(COL_USER_STATUS)
                );
                user.setCreatedAt(rs.getTimestamp(COL_CREATED_AT));
            }
        } catch (SQLException e) {
            Message.error("Database error while fetching user: " + e.getMessage());
        }

        return user;
    }

    @Override
    public boolean addUser(User user) {
        String sql = "INSERT INTO " + TABLE_NAME + " (" +
                COL_FIRST_NAME + ", " +
                COL_MIDDLE_NAME + ", " +
                COL_LAST_NAME + ", " +
                COL_CONTACT_NO + ", " +
                COL_ROLE + ", " +
                COL_USERNAME + ", " +
                COL_PASSWORD + ", " +
                COL_USER_STATUS + ") " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getMiddleName());
            stmt.setString(3, user.getLastName());
            stmt.setString(4, user.getContactNo());
            stmt.setString(5, user.getRole());
            stmt.setString(6, user.getUsername());
            stmt.setString(7, user.getPassword());
            stmt.setString(8, user.getUserStatus());

            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            Message.error("Database error while creating user: " + e.getMessage());
            return false;
        }
    }
}