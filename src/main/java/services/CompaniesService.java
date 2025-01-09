package services;
import config.DBConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp; 

public class CompaniesService {
    
    public boolean addCompany(int companyId, String companyName, String companyAddress, Timestamp createdAt) {
        String sql = "INSERT INTO companies VALUES (?, ?, ?, ?)";
        try (Connection connection = DBConfig.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
    
            // Debugging connection
            System.out.println("Connection Test: " + connection);
    
            // Set query parameters
            stmt.setInt(1, companyId);
            stmt.setString(2, companyName);
            stmt.setString(3, companyAddress);
            stmt.setTimestamp(4, createdAt);
    
            // Execute the update
            int rowsAffected = stmt.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
    
            return rowsAffected > 0; // True if insert is successful
    
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            System.err.println("Message: " + e.getMessage());
            return false;
        }
    }

    public String getCompanyName(int companyId) {
    String sql = "SELECT company_name FROM companies WHERE company_id = ?";
    try (Connection connection = DBConfig.getConnection();
         PreparedStatement stmt = connection.prepareStatement(sql)) {

        // Debugging connection
        System.out.println("Connection Test: " + connection);

        // Set the query parameter
        stmt.setInt(1, companyId);

        // Execute the query
        try (ResultSet resultSet = stmt.executeQuery()) {
            if (resultSet.next()) {
                // Retrieve and return the company name
                return resultSet.getString("company_name");
            } else {
                // No company found
                System.err.println("No company found with ID: " + companyId);
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

public boolean setCompanyName(int companyId, String newCompanyName) {
    String sql = "UPDATE companies SET company_name = ? WHERE company_id = ?";
    try (Connection connection = DBConfig.getConnection();
         PreparedStatement stmt = connection.prepareStatement(sql)) {

        // Debugging connection
        System.out.println("Connection Test: " + connection);

        // Set the query parameters
        stmt.setString(1, newCompanyName);
        stmt.setInt(2, companyId);

        // Execute the update
        int rowsAffected = stmt.executeUpdate();
        System.out.println("Rows affected: " + rowsAffected);

        return rowsAffected > 0; // True if the update is successful

    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("SQLState: " + e.getSQLState());
        System.err.println("Error Code: " + e.getErrorCode());
        System.err.println("Message: " + e.getMessage());
        return false;
    }
}

public String getAddress(int companyId) {
    String sql = "SELECT company_address FROM companies WHERE company_id = ?";
    try (Connection connection = DBConfig.getConnection();
         PreparedStatement stmt = connection.prepareStatement(sql)) {

        // Debugging connection
        System.out.println("Connection Test: " + connection);

        // Set the query parameter
        stmt.setInt(1, companyId);

        // Execute the query
        try (ResultSet resultSet = stmt.executeQuery()) {
            if (resultSet.next()) {
                // Retrieve and return the company address
                return resultSet.getString("company_address");
            } else {
                // No company found
                System.err.println("No company found with ID: " + companyId);
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

public boolean setAddress(int companyId, String newAddress) {
    String sql = "UPDATE companies SET company_address = ? WHERE company_id = ?";
    try (Connection connection = DBConfig.getConnection();
         PreparedStatement stmt = connection.prepareStatement(sql)) {

        // Debugging connection
        System.out.println("Connection Test: " + connection);

        // Set the query parameters
        stmt.setString(1, newAddress);
        stmt.setInt(2, companyId);

        // Execute the update
        int rowsAffected = stmt.executeUpdate();
        System.out.println("Rows affected: " + rowsAffected);

        return rowsAffected > 0; // True if the update is successful

    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("SQLState: " + e.getSQLState());
        System.err.println("Error Code: " + e.getErrorCode());
        System.err.println("Message: " + e.getMessage());
        return false;
    }
}


    public static void main(String[] args) {
        // CompaniesService c = new CompaniesService();

        // int companyId1 = 101;
        // String companyName1 = "Tech Innovations Ltd.";
        // String companyAddress1 = "123 Tech Street, Silicon Valley, CA";
        // Timestamp createdAt1 = new Timestamp(System.currentTimeMillis()); // Current timestamp

        // boolean success1 = c.addCompany(companyId1, companyName1, companyAddress1, createdAt1);
        // System.out.println("Company 1 added successfully: " + success1);
        // System.out.println(c.getCompanyName(101));
         //System.out.println(c.setCompanyName(101,  "ZIM"));
        //  System.out.println(c.getAddress(101));
       // System.out.println(c.setAddress(101,  "Dr Yehuda Perach"));

    }


    
}

