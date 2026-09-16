package kcart.daoimpl;

import java.sql.Connection;
import kcart.dao.CustomerDAO;
import kcart.model.Customer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import kcart.util.DBConnection;
import kcart.util.Message;

public class CustomerDAOImpl implements CustomerDAO {

    private static final String TABLE_NAME = "tbl_customer";
    private static final String COL_ID = "customer_id";
    private static final String COL_FIRST_NAME = "first_name";
    private static final String COL_MIDDLE_NAME = "middle_name";
    private static final String COL_LAST_NAME = "last_name";
    private static final String COL_CONTACT_NO = "contact_no";
    private static final String COL_EMAIL = "email";
    private static final String COL_ADDRESS = "address";
    private static final String COL_DRIVER_LICENSE = "driver_license";
    private static final String COL_SECONDARY_ID = "secondary_id";
    private static final String COL_PHOTO = "customer_photo";
    private static final String COL_STATUS = "customer_status";
    private static final String COL_CREATED_AT = "created_at";

    private Connection conn;

    public CustomerDAOImpl() {
        this.conn = DBConnection.getConnection(); // Establish connection.
    }

    // Inserts customer record to custome table.
    @Override
    public boolean addCustomer(Customer customer) {
        String sql = "INSERT INTO " + TABLE_NAME + " ("
                + COL_FIRST_NAME + ", "
                + COL_MIDDLE_NAME + ", "
                + COL_LAST_NAME + ", "
                + COL_CONTACT_NO + ", "
                + COL_EMAIL + ", "
                + COL_ADDRESS + ", "
                + COL_DRIVER_LICENSE + ", "
                + COL_SECONDARY_ID + ", "
                + COL_PHOTO + ", "
                + COL_STATUS + ") "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, customer.getFirstName());
            stmt.setString(2, customer.getMiddleName());
            stmt.setString(3, customer.getLastName());
            stmt.setString(4, customer.getContactNo());
            stmt.setString(5, customer.getEmail());
            stmt.setString(6, customer.getAddress());
            stmt.setBytes(7, customer.getDriverLicense());
            stmt.setBytes(8, customer.getSecondaryId());
            stmt.setBytes(9, customer.getCustomerPhoto());
            stmt.setString(10, customer.getCustomerStatus());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            Message.error("Error adding customer:\n" + e.getMessage());
            return false;
        }
    }

    // Retrieve all necessary customer details for populating the customer table.
    @Override
    public List<Customer> getAllCustomers() {
        {
            List<Customer> list = new ArrayList<>();
            String sql = "SELECT * FROM " + TABLE_NAME;

            try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    Customer c = new Customer(
                            rs.getInt(COL_ID),
                            rs.getString(COL_FIRST_NAME),
                            rs.getString(COL_MIDDLE_NAME),
                            rs.getString(COL_LAST_NAME),
                            rs.getString(COL_CONTACT_NO),
                            rs.getString(COL_EMAIL),
                            rs.getString(COL_ADDRESS),
                            rs.getString(COL_STATUS),
                            rs.getTimestamp(COL_CREATED_AT)
                    );
                    list.add(c);
                }
            } catch (SQLException e) {
                Message.error("Error retrieving all customers:\n" + e.getMessage());
            }
            return list;
        }
    }
    
}