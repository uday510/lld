package com.app.patterns.creational.factory;

public class TransportService {

    public static void main(String[] args) {
        Transport vehicle = TransportFactory.createTransport("bus");

        vehicle.deliver();
    }
}
