package com.app.pblms.parking_lot.spot;

import com.app.pblms.parking_lot.vehicle.Vehicle;
import com.app.pblms.parking_lot.vehicle.VehicleSize;

public class ParkingSpot {

    private final int spotNumber;
    private final VehicleSize size;

    private Vehicle vehicle;

    public ParkingSpot(int spotNumber, VehicleSize size) {
        this.spotNumber = spotNumber;
        this.size = size;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public VehicleSize getSize() {
        return size;
    }

    public boolean isAvailable() {
        return vehicle == null;
    }

    public boolean canFitVehicle(Vehicle vehicle) {
        return vehicle.getSize().ordinal() <= size.ordinal();
    }

    public void occupy(Vehicle vehicle) {

        if (!isAvailable()) {
            throw new IllegalStateException("Spot already occupied");
        }

        this.vehicle = vehicle;
    }

    public void vacate() {
        this.vehicle = null;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}
