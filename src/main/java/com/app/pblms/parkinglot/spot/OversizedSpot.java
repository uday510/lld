
package com.app.pblms.parkinglot.spot;

import com.app.pblms.parkinglot.vehicle.Vehicle;
import com.app.pblms.parkinglot.vehicle.VehicleSize;

public class OversizedSpot implements ParkingSpot {
    private int spotNumber;
    private Vehicle vehicle;

    public OversizedSpot(int spotNumber, Vehicle vehicle) {
        this.spotNumber = spotNumber;
        this.vehicle = vehicle;
    }

    @Override
    public int getSpotNumber() {
        return spotNumber;
    }

    @Override
    public boolean isAvailable() {
        return vehicle == null;
    }

    @Override
    public void occupy(Vehicle vehicle) {
        if (isAvailable()) this.vehicle = vehicle;
    }

    @Override
    public void vacate() {
        this.vehicle = null;
    }

    @Override
    public VehicleSize getSize() {
        return VehicleSize.LARGE;
    }

}
