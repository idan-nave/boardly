package com.company.onboarding.backend.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConfig {
    // DB_URL=jdbc:mysql://localhost:3306/onboardly_db
    // DB_USER=root
    // DB_PASS=secretpassword
    // Get DB credentials from environment variables
    private static final String DB_URL = "jdbc:mysql://localhost:3306/onboardly_db";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "secretpassword";

    private static Connection connection;

    private DBConfig() {}

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        }
        return connection;
    }
}
