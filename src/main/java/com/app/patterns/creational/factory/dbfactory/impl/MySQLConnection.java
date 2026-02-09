package com.app.patterns.creational.factory.dbfactory.impl;

import com.app.patterns.creational.factory.dbfactory.DBConnection;
import com.app.patterns.creational.factory.dbfactory.DBFactory;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnection implements DBConnection {

    static {
        DBFactory.register("mysql", MySQLConnection::new);
    }

    @Override
    public Connection connect() {

        try {
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/db0",
                    "uday",
                    "Uday@123"
            );

        } catch (Exception e) {
            throw new RuntimeException("MySQL Connection Failed", e);
        }

    }

}
