package com.app.pblms.parking_lot.spot;

import com.app.pblms.parking_lot.vehicle.Vehicle;
import com.app.pblms.parking_lot.vehicle.VehicleSize;

/**
 * ParkingSpot interface represents a single parking space in the lot.
 * Different types of spots (regular, EV, VIP, etc.) can implement this interface
 */
public interface ParkingSpot {

    /**
     * Checks whether the parking spot is available.
     * @return true if the spot is free, false is occupied.
     */
    boolean isAvailable();

    /**
     * Occupies this spot with a vehicle.
     * @param vehicle the vehicle to park
     */
    void occupy(Vehicle vehicle);

    /**
     * vacates this parking spot, making it available again
     */
    void vacate();

    /**
     * @return the spot number
     */
    int getSpotNumber();

    /**
     * @return the size of the spot (SMALL, MEDIUM, LARGE)
     */
    VehicleSize getSize();

}
