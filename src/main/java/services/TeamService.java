package services;
import config.DBConfig;
import java.sql.*;
import java.util.ArrayList;

public class TeamService {

    // Method to get all team names
    public ArrayList<String> getAllTeamNames() {
        String sql = "SELECT team_name FROM teams";
        ArrayList<String> teamNames = new ArrayList<>(); // Initialize the ArrayList

        try (Connection connection = DBConfig.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet response = stmt.executeQuery()) {

            // Process the ResultSet
            while (response.next()) {
                teamNames.add(response.getString("team_name"));
            }

        } catch (SQLException e) {
            // Log or handle SQL exceptions
            System.err.println("Error while fetching team names: " + e.getMessage());
        }

        return teamNames; // Return the list, empty if no teams or on failure
    }

    // Method to add a team
    public boolean addTeam(int teamId, int companyId, String teamName, int processId, Timestamp createdAt) {
        String sql = "INSERT INTO teams (team_id, company_id, team_name, process_id, created_at) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DBConfig.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            // Debugging connection
            System.out.println("Connection Test: " + connection);

            // Set query parameters
            stmt.setInt(1, teamId);
            stmt.setInt(2, companyId);
            stmt.setString(3, teamName);
            stmt.setInt(4, processId);
            stmt.setTimestamp(5, createdAt);

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

    // Method to get team name by team ID
    public String getTeamName(int teamId) {
        String sql = "SELECT team_name FROM teams WHERE team_id = ?";

        try (Connection connection = DBConfig.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            // Set the query parameter for team_id
            stmt.setInt(1, teamId);

            // Execute the query
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    // Retrieve and return the team name
                    return resultSet.getString("team_name");
                } else {
                    // No team found with the given team_id
                    System.err.println("No team found with ID: " + teamId);
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

    // Method to set team name by team ID
    public boolean setTeamName(int teamId, String newTeamName) {
        String sql = "UPDATE teams SET team_name = ? WHERE team_id = ?";

        try (Connection connection = DBConfig.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            // Set query parameters
            stmt.setString(1, newTeamName);  // Set the new team name
            stmt.setInt(2, teamId);  // Set the team_id for which we are updating the name

            // Execute the update
            int rowsAffected = stmt.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);

            return rowsAffected > 0;  // Return true if update was successful

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            System.err.println("Message: " + e.getMessage());
            return false;  // Return false in case of an error
        }
    }

    // Method to get team ID by process ID
    public Integer getTeamIdByProcessId(int processId) {
        String sql = "SELECT team_id FROM teams WHERE process_id = ?";

        try (Connection connection = DBConfig.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            // Set the query parameter for process_id
            stmt.setInt(1, processId);

            // Execute the query
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    // Retrieve and return the team_id
                    return resultSet.getInt("team_id");
                } else {
                    // No team found with the given process_id
                    System.err.println("No team found with process_id: " + processId);
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

    // Method to get process ID by team ID
    public Integer getProcessIdByTeamId(int teamId) {
        String sql = "SELECT process_id FROM teams WHERE team_id = ?";

        try (Connection connection = DBConfig.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            // Set the query parameter for team_id
            stmt.setInt(1, teamId);

            // Execute the query
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    // Retrieve and return the process_id
                    return resultSet.getInt("process_id");
                } else {
                    // No process found with the given team_id
                    System.err.println("No process found with team_id: " + teamId);
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

    // Method to assign a process to a team
    public boolean assignProcessToTeam(int teamId, int processId) {
        try (Connection connection = DBConfig.getConnection()) {
            String sql = "UPDATE teams SET process_id = ? WHERE id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, processId);
                stmt.setInt(2, teamId);
                int rowsAffected = stmt.executeUpdate();

                return rowsAffected > 0; // If rows affected > 0, the update was successful
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // If an error occurs, return false
        }
    }

    @Override
    public String toString() {
        return "TeamService []";
    }
}
