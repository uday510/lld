package com.app.pblms.parking_lot.vehicle;

/**
 * Represents a small-sized motorcycle
 * Implements the Vehicle interface
 */
public class Truck implements Vehicle {
    private final String licensePlate;

    public Truck(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public VehicleSize getSize() {
        return VehicleSize.LARGE;
    }

    @Override
    public String getLicensePlate() {
        return licensePlate;
    }
}
