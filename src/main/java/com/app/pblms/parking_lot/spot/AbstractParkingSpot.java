package com.app.pblms.parking_lot.spot;
import com.app.pblms.parking_lot.vehicle.Vehicle;
import com.app.pblms.parking_lot.vehicle.VehicleSize;

public class AbstractParkingSpot implements ParkingSpot {
    
    private final int spotNumber;
    private Vehicle vehicle;
    private boolean available;

    public AbstractParkingSpot(int spotNumber) {
        this.spotNumber = spotNumber;
        this.vehicle = null;
        this.available = true;
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public synchronized void occupy(Vehicle vehicle) {
        if (!available) {
            throw new IllegalStateException("Spot " + this.spotNumber + " unavailable at the moment.");
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
