package com.app.pblms.parking_lot.spot;
import com.app.pblms.parking_lot.vehicle.Vehicle;
import com.app.pblms.parking_lot.vehicle.VehicleSize;

public class RegularSpot implements ParkingSpot {

    private final int spotNumber;
    private Vehicle vehicle;
    private boolean available;

    public RegularSpot (int spotNumber) {
        this.spotNumber = spotNumber;
        this.available = true;
        this.vehicle = null;
    }

    @Override
    public boolean isAvailable() {
        return this.available;
    }

    @Override
    public synchronized void occupy(Vehicle vehicle) {
        if (!available) {
            throw new IllegalStateException("Spot " + this.spotNumber + " unavailable.");
        }

        this.vehicle = vehicle;
        this.available = false;
    }

    @Override
    public synchronized void vacate() {
        this.vehicle = null;
        this.available = true;
    }

    @Override
    public int getSpotNumber() {
        return this.spotNumber;
    }

    @Override
    public VehicleSize getSize() {
        return VehicleSize.MEDIUM;
    }

    @Override
    public String toString() {
        return STR."ParkingSpot{spotNumber=\{spotNumber}, size=\{getSize()}, available=\{available}}";
    }
}
