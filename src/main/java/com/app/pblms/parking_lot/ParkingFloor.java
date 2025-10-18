package com.app.pblms.parking_lot;


import com.app.pblms.parking_lot.spot.ParkingSpot;
import com.app.pblms.parking_lot.vehicle.Vehicle;
import com.app.pblms.parking_lot.vehicle.VehicleSize;

import java.util.*;

/**
 * Represents a single floor in the parking lot.
 * Each floor manages a collection of parking spots, categorized by vehicle size.
 */
public class ParkingFloor {

    private final int floorNumber;
    private final Map<VehicleSize, List<ParkingSpot>> availableSpots;

    public ParkingFloor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.availableSpots = new EnumMap<>(VehicleSize.class);

        for (VehicleSize size : VehicleSize.values()) {
            availableSpots.put(size, new ArrayList<>());
        }
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    /**
     * Adds a new parking spot to the floor.
     */
    public void addSpot(ParkingSpot parkingSpot) {
        availableSpots.get(parkingSpot.getSize()).add(parkingSpot);
    }

    /**
     * Finds an available spot for the given vehicle.
     * @param vehicle vehicle to park
     * @return available ParkingSpot or null if none
     */
    public ParkingSpot findSpotForVehicle(Vehicle vehicle) {
        VehicleSize vehicleSize = vehicle.getSize();
        for (VehicleSize size : VehicleSize.values()) {
            if (size.ordinal() < vehicleSize.ordinal()) continue;
            for (ParkingSpot parkingSpot : availableSpots.get(size)) {
                if (parkingSpot.isAvailable()) {
                    return parkingSpot;
                }
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return STR."ParkingFloor{floorNumber=\{floorNumber}, availableSpots=\{availableSpots}}";
    }
}
