package com.app.pblms.basic.parking_lot.vehicle;

public class Motorcycle extends AbstractVehicle {

    public Motorcycle(String licensePlate) {
        super(licensePlate);
    }

    public VehicleSize getSize() {
        return VehicleSize.SMALL;
    }
}
