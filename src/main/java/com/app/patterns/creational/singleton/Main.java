package com.app.patterns.creational.singleton;

public class Main {

    static void main() {

        ConnectionPool a = ConnectionPool.getInstance();
        ConnectionPool b = ConnectionPool.getInstance();

        System.out.println("Same instance? " + (a == b));

        a.acquire();
        b.acquire();
        a.release();
    }
}
