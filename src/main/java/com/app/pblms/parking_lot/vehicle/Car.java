package com.app.pblms.parking_lot.vehicle;

public class Car extends AbstractVehicle {

    public Car(String licensePlate) {
        super(licensePlate);
    }

    @Override
    public VehicleSize getSize() {
        return VehicleSize.MEDIUM;
    }

}
