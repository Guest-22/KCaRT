package kcart.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Rental {

    private int rentalId;
    private int customerId;
    private String customerName; // Fetch using customer_id.
    private int carId;
    private String carInfo; // Fetch using car_id.
    private int processedBy; // Fetch using ActiveSession.
    private String processedByName;
    private Date startDate;
    private Date expectedReturnDate;
    private String rentalStatus;
    private Timestamp createdAt;

    // Model for adding new rental entry to DB; Used by Add Rental/Reservation form.
    public Rental(int customerId, int carId, int processedBy, Date startDate, Date expectedReturnDate, String rentalStatus) {
        this.customerId = customerId;
        this.carId = carId;
        this.processedBy = processedBy;
        this.startDate = startDate;
        this.expectedReturnDate = expectedReturnDate;
        this.rentalStatus = rentalStatus;
    }

    // Model for editing rental (start and return date are only editable; rest are not).
    public Rental(int rentalId, int customerId, int carId, int processedBy, Date startDate, Date expectedReturnDate) {
        this.rentalId = rentalId;
        this.customerId = customerId;
        this.carId = carId;
        this.processedBy = processedBy;
        this.startDate = startDate;
        this.expectedReturnDate = expectedReturnDate;
    }

    // Model for retrieving all the necessary rental info. for Rental Menu.
    public Rental(int rentalId, int customerId, String customerName,
            int carId, String carInfo, int processedBy, String processedByName,
            Date startDate, Date expectedReturnDate,
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

    public Rental(int rentalId, Date startDate, Date expectedReturnDate) {
        this.rentalId = rentalId;
        this.startDate = startDate;
        this.expectedReturnDate = expectedReturnDate;
    }

    // Getter/Setter method.
    public int getRentalId() {
        return rentalId;
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

    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public void setExpectedReturnDate(Date expectedReturnDate) {
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
