/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ravin
 */
public class DatabaseConnection {
     public static Connection connect() {
        Connection conn = null;

        try {
            // Load the MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Define DB connection URL
            String url = "jdbc:mysql://localhost:3306/oilmart";  // Replace with your DB name
            String user = "root";       // Default MySQL username
            String password = "";       // Your MySQL password (empty by default)

            // Establish connection
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Database Connected successfully!");

        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }

        return conn;
    }

    // Optional: Run to test connection directly
    public static void main(String[] args) {
        connect();
    }
    
}
