package com.app.patterns.creational.factory.dbfactory.impl;

import com.app.patterns.creational.factory.dbfactory.DBConnection;
import com.app.patterns.creational.factory.dbfactory.DBFactory;

import java.sql.Connection;
import java.sql.DriverManager;

public class PostgresConnection implements DBConnection {

    static {
        DBFactory.register("postgres", PostgresConnection::new);
    }

    @Override
    public Connection connect() {

        try {
            return DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/mydb",
                    "username",
                    "password"
            );

        } catch (Exception e) {
            throw new RuntimeException("Postgres connection failed", e);
        }
    }

}
