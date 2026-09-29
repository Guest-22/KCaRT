package kcart.daoimpl;

import kcart.dao.ReturnDAO;
import kcart.model.Return;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import kcart.util.DBConnection;
import kcart.util.Message;

public class ReturnDAOImpl implements ReturnDAO {

    private static final String TABLE_NAME = "tbl_return";
    private static final String COL_RETURN_ID = "return_id";
    private static final String COL_RENTAL_ID = "rental_id";
    private static final String COL_PROCESSED_BY = "processed_by";
    private static final String COL_RETURN_DATE = "return_date";
    private static final String COL_CONDITION = "return_condition";
    private static final String COL_REMARKS = "remarks";
    private static final String COL_CREATED_AT = "created_at";

    private Connection conn;

    public ReturnDAOImpl() {
        this.conn = DBConnection.getConnection(); // Establish connection.
    }

    // Inserts return info.
    @Override
    public int addReturn(Return returnInfo) {
        String sql = "INSERT INTO " + TABLE_NAME + " ("
                + COL_RENTAL_ID + ", "
                + COL_PROCESSED_BY + ", "
                + COL_RETURN_DATE + ", "
                + COL_CONDITION + ", "
                + COL_REMARKS + ") VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(
                sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, returnInfo.getRentalId());
            stmt.setInt(2, returnInfo.getProcessedBy());
            stmt.setDate(3, returnInfo.getReturnDate());
            stmt.setString(4, returnInfo.getCondition());
            stmt.setString(5, returnInfo.getRemarks());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            Message.error("Error adding return:\n" + e.getMessage());
        }
        return -1;
    }

    @Override
    public List<Return> getAllReturns() {
        List<Return> list = new ArrayList<>();

        String sql = "SELECT r.*, "
                + "CONCAT(u.last_name, ', ', u.first_name) AS processed_by_name "
                + "FROM tbl_return r "
                + "INNER JOIN tbl_user u "
                + "ON r.processed_by = u.user_id";

        try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Return r = new Return(
                        rs.getInt(COL_RETURN_ID),
                        rs.getInt(COL_RENTAL_ID),
                        rs.getInt(COL_PROCESSED_BY),
                        rs.getString("processed_by_name"),
                        rs.getDate(COL_RETURN_DATE),
                        rs.getString(COL_CONDITION),
                        rs.getString(COL_REMARKS),
                        rs.getTimestamp(COL_CREATED_AT)
                );

                list.add(r);
            }

        } catch (SQLException e) {
            Message.error("Error retrieving all returns:\n" + e.getMessage());
        }

        return list;
    }

}
