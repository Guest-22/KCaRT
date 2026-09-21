package kcart.util;

import java.sql.Connection;
import java.sql.DriverManager;

// Connects the system to the local database.
public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/kcart_db"; 
    private static final String USER = "root"; 
    private static final String PASSWORD = ""; 
    
    // Attempts to get a database connection.
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Helps locate MySQL JDBC Driver (Connector/J, version: 9.3.0).
            return DriverManager.getConnection(URL, USER, PASSWORD); // Return DB connection.
        } catch (Exception e) {
            // Catch and print the error; failed DB connection.
            Message.error("Database Connection Error:\nPlease ensure the database service is running and try again.");
            return null;
        }
    }
}