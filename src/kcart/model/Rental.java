package kcart.model;

import java.time.LocalDateTime;
import java.sql.Timestamp;

public class Rental {

    private int rentalId;
    private int customerId;
    private String customerName; // Fetch using customer_id.
    private int carId;
    private String carInfo; // Fetch using car_id.
    private String carBrand;
    private String carModel;
    private int processedBy; // Fetch using ActiveSession.
    private String processedByName;
    private LocalDateTime startDate;
    private LocalDateTime expectedReturnDate;
    private String rentalStatus;
    private Timestamp createdAt;

    // Model for viewing invoice in billing module.
    public Rental(String customerName, String carBrand, String carModel) {
        this.customerName = customerName;
        this.carBrand = carBrand;
        this.carModel = carModel;
    }

    // Model for viewing recent rentals in Admin Dashboard.
    public Rental(int rentalId, String customerName, String carInfo,
            LocalDateTime startDate, LocalDateTime expectedReturnDate,
            String rentalStatus) {
        this.rentalId = rentalId;
        this.customerName = customerName;
        this.carInfo = carInfo;
        this.startDate = startDate;
        this.expectedReturnDate = expectedReturnDate;
        this.rentalStatus = rentalStatus;
    }

    // Model for adding new rental entry to DB.
    public Rental(int customerId, int carId, int processedBy,
            LocalDateTime startDate, LocalDateTime expectedReturnDate,
            String rentalStatus) {
        this.customerId = customerId;
        this.carId = carId;
        this.processedBy = processedBy;
        this.startDate = startDate;
        this.expectedReturnDate = expectedReturnDate;
        this.rentalStatus = rentalStatus;
    }

    // Model for editing rental.
    public Rental(int rentalId, int customerId, int carId, int processedBy,
            LocalDateTime startDate, LocalDateTime expectedReturnDate) {
        this.rentalId = rentalId;
        this.customerId = customerId;
        this.carId = carId;
        this.processedBy = processedBy;
        this.startDate = startDate;
        this.expectedReturnDate = expectedReturnDate;
    }

    public Rental(int rentalId, int customerId, int carId,
            LocalDateTime startDate, LocalDateTime expectedReturnDate) {
        this.rentalId = rentalId;
        this.customerId = customerId;
        this.carId = carId;
        this.startDate = startDate;
        this.expectedReturnDate = expectedReturnDate;
    }

    // Model for retrieving all necessary rental info. for Rental Menu.
    public Rental(int rentalId, int customerId, String customerName,
            int carId, String carInfo, int processedBy, String processedByName,
            LocalDateTime startDate, LocalDateTime expectedReturnDate,
            String rentalStatus, Timestamp createdAt) {
        this.rentalId = rentalId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.carId = carId;
        this.carInfo = carInfo;
        this.processedBy = processedBy;
        this.processedByName = processedByName;
        this.startDate = startDate;
        this.expectedReturnDate = expectedReturnDate;
        this.rentalStatus = rentalStatus;
        this.createdAt = createdAt;
    }

    public Rental(int rentalId, LocalDateTime startDate,
            LocalDateTime expectedReturnDate) {
        this.rentalId = rentalId;
        this.startDate = startDate;
        this.expectedReturnDate = expectedReturnDate;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    // Getter/Setter methods.
    public int getRentalId() {
        return rentalId;
    }

    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
    }

    public int getProcessedBy() {
        return processedBy;
    }

    public void setProcessedBy(int processedBy) {
        this.processedBy = processedBy;
    }

    public String getProcessedByName() {
        return processedByName;
    }

    public void setProcessedByName(String processedByName) {
        this.processedByName = processedByName;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getCarInfo() {
        return carInfo;
    }

    public void setCarInfo(String carInfo) {
        this.carInfo = carInfo;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public void setExpectedReturnDate(LocalDateTime expectedReturnDate) {
        this.expectedReturnDate = expectedReturnDate;
    }

    public String getRentalStatus() {
        return rentalStatus;
    }

    public void setRentalStatus(String rentalStatus) {
        this.rentalStatus = rentalStatus;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
