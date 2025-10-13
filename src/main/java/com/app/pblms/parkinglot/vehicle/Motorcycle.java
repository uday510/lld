package com.app.pblms.parkinglot.vehicle;

/**
 * Represents a small-sized motorcycle
 * Implements the Vehicle interface
 */
public class Motorcycle implements Vehicle {

    private final String licensePlate;

    public Motorcycle(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    @Override
    public String getLicensePlate() {
        return this.licensePlate;
    }

    @Override
    public VehicleSize getSize() {
        return VehicleSize.SMALL;
    }

    @Override
    public String toString() {
        return getLicensePlate();
    }

}
