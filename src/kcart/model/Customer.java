package kcart.model;

import java.sql.Timestamp;

public class Customer {

    private int customerId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String contactNo;
    private String email;
    private String address;
    private byte[] driverLicense;
    private byte[] secondaryId;
    private byte[] customerPhoto;
    private String customerStatus;
    private java.sql.Timestamp createdAt;

    // Constructor for adding new customer (ID auto-increment, createdAt auto-timestamp).
    public Customer(String firstName, String middleName, String lastName,
            String contactNo, String email, String address,
            String customerStatus) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.contactNo = contactNo;
        this.email = email;
        this.address = address;
        this.customerStatus = customerStatus;
    }

    // Constructor for retrieving list of customers from the DB; used for CustomerMenu.
    public Customer(int customerId, String firstName, String middleName, String lastName,
            String contactNo, String email, String address,
            String customerStatus, Timestamp createdAt) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.contactNo = contactNo;
        this.email = email;
        this.address = address;
        this.customerStatus = customerStatus;
        this.createdAt = createdAt;
    }
    
     // Constructor for retrieving from DB by ID; Used for EditCustomer.
    public Customer(int customerId, String firstName, String middleName, String lastName,
            String contactNo, String email, String address,
            String customerStatus, byte[] driverLicense,
            byte[] secondaryId, byte[] customerPhoto,
            Timestamp createdAt) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.contactNo = contactNo;
        this.email = email;
        this.address = address;
        this.customerStatus = customerStatus;
        this.driverLicense = driverLicense;
        this.secondaryId = secondaryId;
        this.customerPhoto = customerPhoto;
        this.createdAt = createdAt;
    }

    public int getCustomerId() {
        return customerId;
    }

    // Getter/Setter method
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public byte[] getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(byte[] driverLicense) {
        this.driverLicense = driverLicense;
    }

    public byte[] getSecondaryId() {
        return secondaryId;
    }

    public void setSecondaryId(byte[] secondaryId) {
        this.secondaryId = secondaryId;
    }

    public byte[] getCustomerPhoto() {
        return customerPhoto;
    }

    public void setCustomerPhoto(byte[] customerPhoto) {
        this.customerPhoto = customerPhoto;
    }

    public String getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(String customerStatus) {
        this.customerStatus = customerStatus;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
