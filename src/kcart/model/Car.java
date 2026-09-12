package kcart.model;

import java.sql.Timestamp;

public class Car {

    private int carId;
    private String plateNo;
    private String brand;
    private String model;
    private int year;
    private String color;
    private String transmissionType;
    private String fuelType;
    private int seatCap;
    private double dailyRate;
    private byte[] carPhoto;
    private String carStatus;
    private java.sql.Timestamp createdAt;

    // Getter/Setter method
    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public int getSeatCap() {
        return seatCap;
    }

    public void setSeatCap(int seatCap) {
        this.seatCap = seatCap;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(double dailyRate) {
        this.dailyRate = dailyRate;
    }

    public byte[] getCarPhoto() {
        return carPhoto;
    }

    public void setCarPhoto(byte[] carPhoto) {
        this.carPhoto = carPhoto;
    }

    public String getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(String carStatus) {
        this.carStatus = carStatus;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}