package services;

import config.DBConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProcessService {

    public static List<String> getAllProcessNames() {
        List<String> processNames = new ArrayList<>();
        try (Connection connection = DBConfig.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT process_name FROM processes")) { // שינוי בשאילתה

            while (resultSet.next()) {
                String processName = resultSet.getString("process_name"); // שינוי בשם המשתנה
                processNames.add(processName);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // טיפול בשגיאות
        }
        return processNames;
    }

    public String getProcessName(int processId) {
        String sql = "SELECT process_name FROM processes WHERE process_id = ?";
    
        try (Connection connection = DBConfig.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
    
            // Debugging connection
            System.out.println("Connection Test: " + connection);
    
            // Set the query parameter for process_id
            stmt.setInt(1, processId);
    
            // Execute the query
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    // Retrieve and return the process_name
                    return resultSet.getString("process_name");
                } else {
                    // No process found with the given process_id
                    System.err.println("No process found with process_id: " + processId);
                    return null;
                }
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            System.err.println("Message: " + e.getMessage());
            return null;
        }
    }
    
    public boolean setProcessName(int processId, String newProcessName) {
        String sql = "UPDATE processes SET process_name = ? WHERE process_id = ?";
    
        try (Connection connection = DBConfig.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
    
            // Set the query parameters
            stmt.setString(1, newProcessName);
            stmt.setInt(2, processId);
    
            // Execute the update
            int rowsAffected = stmt.executeUpdate();
            
            // Check if the update was successful
            if (rowsAffected > 0) {
                System.out.println("Process name updated successfully.");
                return true; // Return true if update was successful
            } else {
                System.err.println("No process found with process_id: " + processId);
                return false; // Return false if no rows were affected (i.e., no such process found)
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            System.err.println("Message: " + e.getMessage());
            return false; // Return false in case of an error
        }
    }
    
    public String getDescriptionByProcessId(int processId) {
        String sql = "SELECT description FROM processes WHERE process_id = ?";
    
        try (Connection connection = DBConfig.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
    
            // Set the query parameter for process_id
            stmt.setInt(1, processId);
    
            // Execute the query
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    // Retrieve and return the description
                    return resultSet.getString("description");
                } else {
                    // No description found with the given process_id
                    System.err.println("No process found with process_id: " + processId);
                    return null;
                }
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            System.err.println("Message: " + e.getMessage());
            return null;
        }
    }
    
    public boolean setDescriptionByProcessId(int processId, String newDescription) {
        String sql = "UPDATE processes SET description = ? WHERE process_id = ?";
    
        try (Connection connection = DBConfig.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
    
            // Set the query parameters
            stmt.setString(1, newDescription);
            stmt.setInt(2, processId);
    
            // Execute the update
            int rowsAffected = stmt.executeUpdate();
            
            // Check if the update was successful
            if (rowsAffected > 0) {
                System.out.println("Process description updated successfully.");
                return true; // Return true if update was successful
            } else {
                System.err.println("No process found with process_id: " + processId);
                return false; // Return false if no rows were affected (i.e., no such process found)
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            System.err.println("Message: " + e.getMessage());
            return false; // Return false in case of an error
        }
    }
    
    public static void main(String[] args) {
        // List<String> processes = getAllProcessNames();
        // System.out.println(processes);

        //  ProcessService p = new ProcessService();
        // System.out.println(p.getProcessName(1));
        // p.setProcessName(3, "null");
        // System.out.println(p.getDescriptionByProcessId(1));
// p.setDescriptionByProcessId(1,"this is a test");
    }
}