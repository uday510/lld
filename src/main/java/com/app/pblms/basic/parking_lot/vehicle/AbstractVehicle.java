package com.app.pblms.basic.parking_lot.vehicle;

public abstract class AbstractVehicle implements Vehicle {

    private final String licensePlate;

    protected AbstractVehicle(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    @Override
    public String getLicensePlate() {
        return licensePlate;
    }
}
