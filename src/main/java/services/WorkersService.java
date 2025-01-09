package services;
import config.DBConfig;
import java.sql.*;
import java.util.ArrayList;

public class WorkersService {


   public boolean addWorker(int workerId, int teamId, String workerName, String status, Date startDate, Date endDate) {
    String sql = "INSERT INTO workers VALUES (?, ?, ?, ?, ?, ?)";
    try (Connection connection = DBConfig.getConnection();
         PreparedStatement stmt = connection.prepareStatement(sql)) {

        // Debugging connection
        System.out.println("Connection Test: " + connection);

        // Set query parameters
        stmt.setInt(1, workerId);
        stmt.setInt(2, teamId);
        stmt.setString(3, workerName);
        stmt.setString(4, status);

        // Convert dates to SQL Date
        java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
        java.sql.Date sqlEndDate = (endDate != null) ? new java.sql.Date(endDate.getTime()) : null;

        stmt.setDate(5, sqlStartDate);
        stmt.setDate(6, sqlEndDate);

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


    // returns array of worker names
    public ArrayList<String> getAllWorkerNames() {
        String sql = "SELECT worker_name FROM workers";
        ArrayList<String> names = new ArrayList<>(); // Initialize the ArrayList

        try (Connection connection = DBConfig.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet response = stmt.executeQuery()) {

            // Process the ResultSet
            while (response.next()) {
                names.add(response.getString("worker_name"));
            }

        } catch (SQLException e) {
            // Log or handle SQL exceptions
            System.err.println("Error while fetching worker names: " + e.getMessage());
        }

        return names; // Return the list, empty if no workers or on failure
    }


    public ArrayList<String> getWorkersByTeamId(int teamId) {
        String sql = "SELECT worker_name FROM workers WHERE team_id = ?";
        ArrayList<String> workerNames = new ArrayList<>(); // Initialize the ArrayList
    
        try (Connection connection = DBConfig.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
    
            // Set the query parameter for team_id
            stmt.setInt(1, teamId);
    
            // Execute the query
            try (ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    // Add each worker's name to the ArrayList
                    workerNames.add(resultSet.getString("worker_name"));
                }
    
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            System.err.println("Message: " + e.getMessage());
        }
    
        return workerNames; // Return the list, empty if no workers or on failure
    }
    
    public String getStatusByWorkerId(int workerId) {
        String sql = "SELECT status FROM workers WHERE worker_id = ?";
        try (Connection connection = DBConfig.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
    
            // Set the query parameter for worker_id
            stmt.setInt(1, workerId);
    
            // Execute the query
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    // Retrieve and return the status
                    return resultSet.getString("status");
                } else {
                    // No worker found with the provided worker_id
                    System.err.println("No worker found with worker_id: " + workerId);
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

    public ArrayList<String> getWorkerNameByStatus(String status) {
        String sql = "SELECT worker_name FROM workers WHERE status = ?";
        ArrayList<String> workerNames = new ArrayList<>(); // Initialize the ArrayList
    
        try (Connection connection = DBConfig.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
    
            // Set the query parameter for status
            stmt.setString(1, status);
    
            // Execute the query
            try (ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    // Add each worker's name to the ArrayList
                    workerNames.add(resultSet.getString("worker_name"));
                }
    
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            System.err.println("Message: " + e.getMessage());
        }
    
        return workerNames; // Return the list, empty if no workers or on failure
    }
    
    public boolean setStatusByWorkerId(int workerId, String newStatus) {
        String sql = "UPDATE workers SET status = ? WHERE worker_id = ?";
        try (Connection connection = DBConfig.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
    
            // Set the query parameters for newStatus and workerId
            stmt.setString(1, newStatus);
            stmt.setInt(2, workerId);
    
            // Execute the update
            int rowsAffected = stmt.executeUpdate();
    
            // Check if update was successful
            return rowsAffected > 0; // If rowsAffected > 0, the update was successful
    
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            System.err.println("Message: " + e.getMessage());
            return false; // If an error occurs, return false
        }
    }
    
     public static void main(String[] args) {
        //   WorkersService ws = new WorkersService();
        
    // //     // Define start and end dates
    // //     Date startDate = Date.valueOf("2025-02-08");
    // //     Date endDate = Date.valueOf("2025-02-19");

    // //     boolean success = ws.addWorker(7, 2, "Adam", "pending", startDate, endDate);
        
    // //     // Print the result
    // //     System.out.println("Worker added successfully: " + success);

    // //     ArrayList<String> a =  ws.getAllWorkerNames();
    // //     for(int i = 0; i < a.size(); i++){
    // //         System.out.println(a.get(i));
    // //     }
            //   System.out.println(ws.getWorkersByTeamId(1));

            // System.out.println(ws.getStatusByWorkerId(1));
            // System.out.println(ws.getWorkerNameByStatus("pending"));
// ws.setStatusByWorkerId(1, "completed");
     }
}

