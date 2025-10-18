package com.app.pblms.parking_lot.vehicle;

/**
 * Represents a small-sized motorcycle
 * Implements the Vehicle interface
 */
public class Car implements Vehicle {
    private final String licensePlate;

    public Car(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public VehicleSize getSize() {
        return VehicleSize.MEDIUM;
    }

    @Override
    public String getLicensePlate() {
        return licensePlate;
    }
}
