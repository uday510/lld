package com.app.pblms.basic.parking_lot.vehicle;

public class Truck extends AbstractVehicle {

    public Truck(String licensePlate) {
        super(licensePlate);
    }

    public VehicleSize getSize() {
        return VehicleSize.LARGE;
    }

}
