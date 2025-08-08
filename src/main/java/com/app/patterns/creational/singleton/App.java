package com.app.patterns.creational.singleton;

import java.sql.Connection;

public class App {

    public static void main(String[] args) {
//        Connection conn1 = DBConnection.getInstance().getConnection();
//        Connection conn2 = DBConnection.getInstance().getConnection();
//        System.out.println("Same connection? " + (conn1 == conn2));

        AppSettings appSettings = AppSettings.getInstance();
        AppSettings appSettings1 = AppSettings.getInstance();

        System.out.println(appSettings1 == appSettings);
    }

}
