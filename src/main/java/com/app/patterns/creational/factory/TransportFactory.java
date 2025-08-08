package com.app.patterns.creational.factory;

public class TransportFactory {

    public static Transport createTransport(String type) {
        switch (type.toLowerCase()) {
            case "car":
                return new Car();
            case "bike":
                return new Bike();
            case "bus":
                return new Bus();
            default:
                throw new IllegalArgumentException("Unsupported : " + type);
        }
    }

}
