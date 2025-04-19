package com.app.patterns.creational.singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/mydb?useSSL=false&serverTimezone=UTC";
    private static final String USER = "uday";
    private static final String PASSWORD = "uday1234";

    private Connection connection;

    private DBConnection() {
        try {
            System.out.println("Establishing DB connection...");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("DB connection established.");
        } catch (SQLException e) {
            System.err.println("Error creating DB connection: " + e.getMessage());
        }
    }

    private static final class InstanceHolder {
        private static final DBConnection INSTANCE = new DBConnection();
    }

    public static DBConnection getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public Connection getConnection() {
        return connection;
    }
}
