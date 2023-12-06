package com.wallet.app.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    static String databaseName = System.getenv("jdbc_url");
    static String user = System.getenv("user");
    static String password = System.getenv("password");
    static Connection connection;

    // Check Exception : 
    // Uncheck Exception :

    public static Connection createConnection() {
        try {
            if (connection == null) {
                connection = DriverManager.getConnection(databaseName, user, password);
                return connection;
            }
            return connection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }
}
