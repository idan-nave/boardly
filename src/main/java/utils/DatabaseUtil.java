import java.sql.*;

public class DatabaseUtil {

    public static boolean authenticateUser(String username, String password) {
        boolean isAuthenticated = false;

        // הגדרת חיבור למסד הנתונים
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/onboardly_db", "root", "secretpassword")) {
            // הודעה שהחיבור למסד נתונים הצליח
            System.out.println("Connected to the database successfully!");

            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, username);
                statement.setString(2, password);

                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    // אם נמצא משתמש שמתאים לשם משתמש ולסיסמה
                    isAuthenticated = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isAuthenticated;
    }
}
