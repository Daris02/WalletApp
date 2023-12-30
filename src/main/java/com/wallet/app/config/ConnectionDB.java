package com.wallet.app.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.wallet.app.exception.DatabaseConnectionException;

public class ConnectionDB {
    private static final String dbUrl = System.getenv("jdbc_url");
    private static final String dbUsername = System.getenv("user");
    private static final String dbPassword = System.getenv("password");

    // Check Exception : 
    // Uncheck Exception :

    public static Connection createConnection(String url, String username, String password) {
        try {
                return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new DatabaseConnectionException(e.getMessage());
        }
    }

    public static Connection createConnection() {
        return createConnection(dbUrl, dbUsername, dbPassword);
    }
}
