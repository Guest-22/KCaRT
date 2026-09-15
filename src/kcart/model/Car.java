package kcart.model;

import java.sql.Timestamp;

public class Car {
    private int carId;
    private String plateNo;
    private String brand;
    private String model;
    private String carType;
    private int year;
    private String color;
    private String transmissionType;
    private String fuelType;
    private int seatCap;
    private double dailyRate;
    private byte[] carPhoto;
    private String carStatus;
    private java.sql.Timestamp createdAt;
    
    public Car(int carId, String plateNo, String brand, String model, String carType,
               int year, String color, String transmissionType, String fuelType,
               int seatCap, double dailyRate, String carStatus, Timestamp createdAt) {
        this.carId = carId;
        this.plateNo = plateNo;
        this.brand = brand;
        this.model = model;
        this.carType = carType;
        this.year = year;
        this.color = color;
        this.transmissionType = transmissionType;
        this.fuelType = fuelType;
        this.seatCap = seatCap;
        this.dailyRate = dailyRate;
        this.carStatus = carStatus;
        this.createdAt = createdAt;
    }
    
    public Car(int carId, String plateNo, String brand, String model, String carType,
               int year, String color, String transmissionType, String fuelType,
               int seatCap, double dailyRate, String carStatus) {
        this.carId = carId;
        this.plateNo = plateNo;
        this.brand = brand;
        this.model = model;
        this.carType = carType;
        this.year = year;
        this.color = color;
        this.transmissionType = transmissionType;
        this.fuelType = fuelType;
        this.seatCap = seatCap;
        this.dailyRate = dailyRate;
        this.carStatus = carStatus;
    }
    
    public Car(String plateNo, String brand, String model, String carType,
               int year, String color, String transmissionType, String fuelType,
               int seatCap, double dailyRate, String carStatus) {
        this.plateNo = plateNo;
        this.brand = brand;
        this.model = model;
        this.carType = carType;
        this.year = year;
        this.color = color;
        this.transmissionType = transmissionType;
        this.fuelType = fuelType;
        this.seatCap = seatCap;
        this.dailyRate = dailyRate;
        this.carStatus = carStatus;
    }

    // Getter/Setter method.
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
    
    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
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