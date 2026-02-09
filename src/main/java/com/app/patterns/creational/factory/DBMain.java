package com.app.patterns.creational.factory;

import com.app.patterns.creational.factory.dbfactory.DBConnection;
import com.app.patterns.creational.factory.dbfactory.DBFactory;
import com.app.patterns.creational.factory.dbfactory.impl.MySQLConnection;
import com.app.patterns.creational.factory.dbfactory.impl.PostgresConnection;

import java.sql.Connection;

public class DBMain {

    static void main() {

        new MySQLConnection();
        new PostgresConnection();

        DBConnection db =
                DBFactory.getConnection("mysql");

        Connection con = db.connect();

        System.out.println("Connected: " + con);

    }

}
