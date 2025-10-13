package com.app.pblms.parkinglot.vehicle;

/**
 * Represents a medium-sized car
 * Implements the Vehicle interface
 */
public class Truck implements Vehicle {

    private final String licensePlate;

    public Truck(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    @Override
    public String getLicensePlate() {
        return this.licensePlate;
    }

    @Override
    public VehicleSize getSize() {
        return VehicleSize.LARGE;
    }

    @Override
    public String toString() {
        return getLicensePlate();
    }

}
