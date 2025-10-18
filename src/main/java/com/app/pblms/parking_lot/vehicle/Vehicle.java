package com.app.pblms.parking_lot.vehicle;

/**
 * Vehicle interface represents a generica vehicle that can enter the parking lot.
 * All specific vehicle types (Car, Truck, Motorcycle, etc.)
 */
public interface Vehicle {

    /**
     * @return the unique license plate number of the vehicle
     */
    String getLicensePlate();

    /**
     * @return the size of the vehicle (SMALL, MEDIUM, LARGE)
     */
    VehicleSize getSize();
}
