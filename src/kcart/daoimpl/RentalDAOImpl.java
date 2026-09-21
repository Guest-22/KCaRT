package kcart.daoimpl;

import java.sql.Connection;
import java.util.List;
import kcart.dao.RentalDAO;
import kcart.model.Rental;
import kcart.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import kcart.util.Message;

public class RentalDAOImpl implements RentalDAO {

    private static final String TABLE_NAME = "tbl_rental";
    private static final String COL_ID = "rental_id";
    private static final String COL_CUSTOMER_ID = "customer_id"; // FK.
    private static final String COL_CAR_ID = "car_id"; // FK.
    private static final String COL_PROCESSED_BY = "processed_by";
    private static final String COL_START_DATE = "start_date";
    private static final String COL_RETURN_DATE = "expected_return_date";
    private static final String COL_STATUS = "rental_status";
    private static final String COL_CREATED_AT = "created_at";

    private Connection conn;

    public RentalDAOImpl() {
        this.conn = DBConnection.getConnection();
    }

    @Override
    public boolean addRental(Rental rental) {
        String sql = "INSERT INTO " + TABLE_NAME + " ("
                + COL_CUSTOMER_ID + ", "
                + COL_CAR_ID + ", "
                + COL_PROCESSED_BY + ", "
                + COL_START_DATE + ", "
                + COL_RETURN_DATE + ", "
                + COL_STATUS + ") VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, rental.getCustomerId());
            stmt.setInt(2, rental.getCarId());
            stmt.setInt(3, rental.getProcessedBy()); // int FK
            stmt.setDate(4, rental.getStartDate());
            stmt.setDate(5, rental.getExpectedReturnDate());
            stmt.setString(6, rental.getRentalStatus());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            Message.error("Error adding rental:\n" + e.getMessage());
            return false;
        }
    }

}
