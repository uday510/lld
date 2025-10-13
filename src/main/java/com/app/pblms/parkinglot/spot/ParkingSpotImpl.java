package com.app.pblms.parkinglot.spot;

import com.app.pblms.parkinglot.vehicle.Vehicle;
import com.app.pblms.parkinglot.vehicle.VehicleSize;

/**
 * Default implementation of ParkingSpot.
 * Each spot can hold one vehicle at a time.
 */
public class ParkingSpotImpl implements ParkingSpot {

    private final int spotNumber;
    private final VehicleSize size;
    private Vehicle vehicle;
    private boolean available;

    public ParkingSpotImpl(int spotNumber, VehicleSize size) {
        this.spotNumber = spotNumber;
        this.size = size;
        this.available = true;
    }

    @Override
    public boolean isAvailable() {
         return this.available;
    }

    @Override
    public void occupy(Vehicle vehicle) {
        if (!available) {
            throw new IllegalStateException("Spot " + spotNumber + " is already occupied.");
        }

        this.vehicle = vehicle;
        this.available = false;
    }

    @Override
    public void vacate() {
        this.vehicle = null;
        this.available = true;
    }

    @Override
    public int getSpotNumber() {
        return this.spotNumber;
    }

    @Override
    public VehicleSize getSize() {
         return this.size;
    }

    @Override
    public String toString() {
        return STR."ParkingSpot{spotNumber=\{spotNumber}, size=\{size}, available=\{available}}";
    }

}
