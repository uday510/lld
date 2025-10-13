package com.app.pblms.parkinglot.vehicle;

/**
 * Represents a medium-sized car
 * Implements the Vehicle interface
 */
public class Car implements Vehicle {

    private final String licensePlate;

    public Car(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    @Override
    public String getLicensePlate() {
        return this.licensePlate;
    }

    @Override
    public VehicleSize getSize() {
        return VehicleSize.MEDIUM;
    }

    @Override
    public String toString() {
        return getLicensePlate();
    }

}
