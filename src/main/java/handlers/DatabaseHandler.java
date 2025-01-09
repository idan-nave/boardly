package serverDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseHandler {
    private static final String DB_URL = "jdbc:sqlite:database.db";

    public static Connection connect() throws Exception {
        return DriverManager.getConnection(DB_URL);
    }

    public static void initDatabase() throws Exception {
        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS items (" +
                         "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                         "name TEXT NOT NULL" +
                         ");";
            stmt.execute(sql);
        }
    }

    public static ResultSet getItems() throws Exception {
        Connection conn = connect();
        Statement stmt = conn.createStatement();
        return stmt.executeQuery("SELECT * FROM items");
    }

    public static void addItem(String name) throws Exception {
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO items (name) VALUES (?)")) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        }
    }

    public static void deleteItem(int id) throws Exception {
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM items WHERE id = ?")) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    public static void updateItem(int id, String newName) throws Exception {
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement("UPDATE items SET name = ? WHERE id = ?")) {
            pstmt.setString(1, newName);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        }
    }
}

