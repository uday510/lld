package com.app.patterns.creational.singleton;

public class ConnectionPool {

    private int connectionCount;

    private ConnectionPool() {
        System.out.println("[init] ConnectionPool created");
        this.connectionCount = 0;
    }

    private static class Holder {
        private static final ConnectionPool INSTANCE = new ConnectionPool();
    }

    public static ConnectionPool getInstance() {
        return Holder.INSTANCE;
    }

    public synchronized void acquire() {
        connectionCount++;
        System.out.println("acquire - total=" + connectionCount);
    }

    public synchronized void release() {
        connectionCount--;
        System.out.println("release - total=" + connectionCount);
    }
}
