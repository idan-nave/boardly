package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import config.DBConfig;

public class UsersService {

    public boolean isUserExists(String username, String password) {
        String sql = "SELECT username FROM users WHERE username = ? AND password = ?";
        try (Connection connection = DBConfig.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
    
            // Set the query parameters for username and password
            stmt.setString(1, username);
            stmt.setString(2, password);
    
            // Execute the query
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    // Return true if a user with the given username and password exists
                    return true;
                } else {
                    // No user found with the given username and password
                    return false;
                }
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            System.err.println("Message: " + e.getMessage());
            return false; // Return false if an error occurs
        }
    }
    
    public String getRoleByUsername(String username) {
        String sql = "SELECT role FROM users WHERE username = ?";
        try (Connection connection = DBConfig.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
    
            // Set the query parameter for the username
            stmt.setString(1, username);
    
            // Execute the query
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    // Retrieve and return the role if a user is found
                    return resultSet.getString("role");
                } else {
                    // No user found with the given username
                    System.err.println("No user found with username: " + username);
                    return null; // Return null if no user found
                }
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            System.err.println("Message: " + e.getMessage());
            return null; // Return null in case of an error
        }
    }
    
    public ArrayList<String> getAllUsers() {
    String sql = "SELECT username FROM users";
    ArrayList<String> users = new ArrayList<>(); // Initialize the ArrayList to hold usernames

    try (Connection connection = DBConfig.getConnection();
         PreparedStatement stmt = connection.prepareStatement(sql);
         ResultSet resultSet = stmt.executeQuery()) {

        // Process the ResultSet to collect all usernames
        while (resultSet.next()) {
            // Add each username to the list
            users.add(resultSet.getString("username"));
        }

    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("SQLState: " + e.getSQLState());
        System.err.println("Error Code: " + e.getErrorCode());
        System.err.println("Message: " + e.getMessage());
    }

    return users; // Return the list, which will be empty if no users exist or if an error occurs
}


public static void main(String[] args) {
    // UsersService u = new UsersService();
    //System.out.println(u.isUserExists("admin_tech" , "password123"));
    // System.out.println(u.getRoleByUsername("admin_tech"));
    // System.out.println(u.getAllUsers());
}

}
