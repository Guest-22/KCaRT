package kcart.model;

import java.sql.*;

public class Return {

    private int returnId;
    private int rentalId;
    private int processedBy;
    private Date returnDate;
    private String condition;
    private String remarks;
    private Timestamp createdAt;

    public Return(int rentalId, int processedBy, Date returnDate, String condition, String remarks) {
        this.rentalId = rentalId;
        this.processedBy = processedBy;
        this.returnDate = returnDate;
        this.condition = condition;
        this.remarks = remarks;
    }

    // Getter/Setter method
    public int getReturnId() {
        return returnId;
    }

    public void setReturnId(int returnId) {
        this.returnId = returnId;
    }

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

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
