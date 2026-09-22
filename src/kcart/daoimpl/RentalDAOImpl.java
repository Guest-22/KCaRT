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
    private static final String COL_PROCESSED_BY = "processed_by"; // FK.
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

    // Retrieves all rental info. for populating the table in Rental Menu frame.
    @Override
    public List<Rental> getAllRentals() {
        List<Rental> list = new ArrayList<>();
        String sql = "SELECT r." + COL_ID + ", r." + COL_CUSTOMER_ID + ", "
                + "CONCAT(c." + "last_name" + ", ', ', c." + "first_name"
                + ", ' ', LEFT(c." + "middle_name" + ",1)) AS customer_name, "
                + "r." + COL_CAR_ID + ", CONCAT(car.brand, ' ', car.model, ' (', car.plate_no, ')') AS car_info, "
                + "r." + COL_PROCESSED_BY + ", u.username AS processed_by_name, "
                + "r." + COL_START_DATE + ", r." + COL_RETURN_DATE + ", r." + COL_STATUS + ", r." + COL_CREATED_AT
                + " FROM " + TABLE_NAME + " r "
                + "JOIN tbl_customer c ON r." + COL_CUSTOMER_ID + " = c.customer_id "
                + "JOIN tbl_car car ON r." + COL_CAR_ID + " = car.car_id "
                + "JOIN tbl_user u ON r." + COL_PROCESSED_BY + " = u.user_id";

        try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Rental rental = new Rental(
                        rs.getInt(COL_ID),
                        rs.getInt(COL_CUSTOMER_ID),
                        rs.getString("customer_name"),
                        rs.getInt(COL_CAR_ID),
                        rs.getString("car_info"),
                        rs.getInt(COL_PROCESSED_BY),
                        rs.getString("processed_by_name"),
                        rs.getDate(COL_START_DATE),
                        rs.getDate(COL_RETURN_DATE),
                        rs.getString(COL_STATUS),
                        rs.getTimestamp(COL_CREATED_AT)
                );
                list.add(rental);
            }
        } catch (SQLException e) {
            Message.error("Error retrieving all rentals:\n" + e.getMessage());
        }
        return list;
    }

    // Updates existing rental info. (start & return date are the only field editable; rest are not).
    @Override
    public boolean editRental(Rental rental) {
        String sql = "UPDATE " + TABLE_NAME + " SET "
                + COL_START_DATE + " = ?, "
                + COL_RETURN_DATE + " = ? "
                + "WHERE " + COL_ID + " = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, rental.getStartDate());
            stmt.setDate(2, rental.getExpectedReturnDate());
            stmt.setInt(3, rental.getRentalId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            Message.error("Error editing rental dates:\n" + e.getMessage());
            return false;
        }
    }

    // Retrieves start and return date for editing purposes in case the customer wants to extend.
    @Override
    public Rental getRentalInfo(int rentalId) {
        String sql = "SELECT " + COL_START_DATE + ", " + COL_RETURN_DATE + " "
                + "FROM " + TABLE_NAME + " "
                + "WHERE " + COL_ID + " = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, rentalId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Rental rental = new Rental(
                            rentalId,
                            rs.getDate(COL_START_DATE),
                            rs.getDate(COL_RETURN_DATE)
                    );
                    return rental;
                }
            }
        } catch (SQLException e) {
            Message.error("Error retrieving rental info:\n" + e.getMessage());
        }
        return null;
    }

}
