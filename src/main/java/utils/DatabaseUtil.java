package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class DatabaseUtil {
    // חיבור לבסיס הנתונים
    public static Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:8889/onboardly_db"; // כתובת ה-URL לבסיס הנתונים
        String user = "root";  // שם המשתמש לבסיס הנתונים
        String password = "secretpassword";  // הסיסמה לבסיס הנתונים

        // חיבור
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;
    }

    // פונקציה לאימות משתמש
    public static boolean authenticateUser(String username, String password) {
        boolean isAuthenticated = false;
        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?;";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, username);  // הגדרת שם המשתמש בשאילתה
                stmt.setString(2, password);  // הגדרת הסיסמה בשאילתה

                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    isAuthenticated = true; // אם נמצאה תוצאה, המשתמש מאומת
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isAuthenticated;
    }
}
