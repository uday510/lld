package com.app.pblms.parking_lot.vehicle;

/**
 * Represents a small-sized motorcycle
 * Implements the Vehicle interface
 */
public class Motorcycle implements Vehicle {
    private final String licensePlate;

    public Motorcycle(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public VehicleSize getSize() {
        return VehicleSize.SMALL;
    }

    @Override
    public String getLicensePlate() {
        return licensePlate;
    }
}
