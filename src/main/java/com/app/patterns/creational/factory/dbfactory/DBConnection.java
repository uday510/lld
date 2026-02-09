package com.app.patterns.creational.factory.dbfactory;

import java.sql.Connection;

public interface DBConnection {

    Connection connect();

}
