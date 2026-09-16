package kcart.daoimpl;

import java.sql.Connection;
import java.util.List;
import kcart.dao.CarDAO;
import kcart.model.Car;
import kcart.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import kcart.util.Message;

public class CarDAOImpl implements CarDAO {
    private static final String TABLE_NAME = "tbl_car";
    private static final String COL_ID = "car_id";
    private static final String COL_PLATE_NO = "plate_no";
    private static final String COL_BRAND = "brand";
    private static final String COL_MODEL = "model";
    private static final String COL_TYPE = "car_type";
    private static final String COL_YEAR = "year";
    private static final String COL_COLOR = "color";
    private static final String COL_TRANSMISSION = "transmission_type";
    private static final String COL_FUEL = "fuel_type";
    private static final String COL_SEAT_CAP = "seat_cap";
    private static final String COL_DAILY_RATE = "daily_rate";
    private static final String COL_PHOTO = "car_photo";
    private static final String COL_STATUS = "car_status";
    private static final String COL_CREATED_AT = "created_at";

    private Connection conn;

    public CarDAOImpl() {
        this.conn = DBConnection.getConnection(); // Establish connection.
    }

    // Retrieve list of cars
    @Override
    public List<Car> getAllCars() {
        List<Car> list = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME;

        try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Car c = new Car(
                        rs.getInt(COL_ID),
                        rs.getString(COL_PLATE_NO),
                        rs.getString(COL_BRAND),
                        rs.getString(COL_MODEL),
                        rs.getString(COL_TYPE),
                        rs.getInt(COL_YEAR),
                        rs.getString(COL_COLOR),
                        rs.getString(COL_TRANSMISSION),
                        rs.getString(COL_FUEL),
                        rs.getInt(COL_SEAT_CAP),
                        rs.getDouble(COL_DAILY_RATE),
                        rs.getString(COL_STATUS),
                        rs.getTimestamp(COL_CREATED_AT)
                );
                list.add(c);
            }
        } catch (SQLException e) {
            Message.error("Error retrieving all cars:\n" + e.getMessage());
        }
        return list;
    }

    // Inserts car details to DB.
    @Override
    public boolean addCar(Car car) {
        String sql = "INSERT INTO " + TABLE_NAME + " ("
                + COL_PLATE_NO + ", "
                + COL_BRAND + ", "
                + COL_MODEL + ", "
                + COL_TYPE + ", "
                + COL_YEAR + ", "
                + COL_COLOR + ", "
                + COL_TRANSMISSION + ", "
                + COL_FUEL + ", "
                + COL_SEAT_CAP + ", "
                + COL_DAILY_RATE + ", "
                + COL_PHOTO + ", "
                + COL_STATUS + ") "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, car.getPlateNo());
            stmt.setString(2, car.getBrand());
            stmt.setString(3, car.getModel());
            stmt.setString(4, car.getCarType());
            stmt.setInt(5, car.getYear());
            stmt.setString(6, car.getColor());
            stmt.setString(7, car.getTransmissionType());
            stmt.setString(8, car.getFuelType());
            stmt.setInt(9, car.getSeatCap());
            stmt.setDouble(10, car.getDailyRate());
            stmt.setBytes(11, car.getCarPhoto());
            stmt.setString(12, car.getCarStatus());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            Message.error("Error adding car:\n" + e.getMessage());
            return false;
        }
    }

    // Update existing car details.
    @Override
    public boolean editCar(Car car) {
        String sql = "UPDATE " + TABLE_NAME + " SET "
                + COL_PLATE_NO + " = ?, "
                + COL_BRAND + " = ?, "
                + COL_MODEL + " = ?, "
                + COL_TYPE + " = ?, "
                + COL_YEAR + " = ?, "
                + COL_COLOR + " = ?, "
                + COL_TRANSMISSION + " = ?, "
                + COL_FUEL + " = ?, "
                + COL_SEAT_CAP + " = ?, "
                + COL_DAILY_RATE + " = ?, "
                + COL_PHOTO + " = ?, "
                + COL_STATUS + " = ? "
                + "WHERE " + COL_ID + " = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, car.getPlateNo());
            stmt.setString(2, car.getBrand());
            stmt.setString(3, car.getModel());
            stmt.setString(4, car.getCarType());
            stmt.setInt(5, car.getYear());
            stmt.setString(6, car.getColor());
            stmt.setString(7, car.getTransmissionType());
            stmt.setString(8, car.getFuelType());
            stmt.setInt(9, car.getSeatCap());
            stmt.setDouble(10, car.getDailyRate());
            stmt.setBytes(11, car.getCarPhoto());
            stmt.setString(12, car.getCarStatus());
            stmt.setInt(13, car.getCarId()); // WHERE car_id = ?

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            Message.error("Error editing car:\n" + e.getMessage());
            return false;
        }
    }

    // Retrieve photo blob by using Car ID as reference.
    @Override
    public byte[] getCarPhotoById(int carId) {
        String sql = "SELECT " + COL_PHOTO + " FROM " + TABLE_NAME + " WHERE " + COL_ID + " = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, carId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Must be getBytes, not getByte
                    return rs.getBytes(COL_PHOTO);
                }
            }
        } catch (SQLException e) {
            Message.error("Error retrieving car photo:\n" + e.getMessage());
        }
        return null;
    }

    @Override
    public Car getCarById(int carId) {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_ID + " = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, carId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Car car = new Car(
                            rs.getInt(COL_ID),
                            rs.getString(COL_PLATE_NO),
                            rs.getString(COL_BRAND),
                            rs.getString(COL_MODEL),
                            rs.getString(COL_TYPE),
                            rs.getInt(COL_YEAR),
                            rs.getString(COL_COLOR),
                            rs.getString(COL_TRANSMISSION),
                            rs.getString(COL_FUEL),
                            rs.getInt(COL_SEAT_CAP),
                            rs.getDouble(COL_DAILY_RATE),
                            rs.getString(COL_STATUS),
                            rs.getTimestamp(COL_CREATED_AT)
                    );

                    car.setCarPhoto(rs.getBytes(COL_PHOTO));

                    return car;
                }
            }
        } catch (SQLException e) {
            Message.error("Error retrieving car by ID:\n" + e.getMessage());
        }
        return null;
    }
}
