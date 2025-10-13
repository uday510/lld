package com.app.pblms.parkinglot.manager;


import com.app.pblms.parkinglot.floor.ParkingFloor;
import com.app.pblms.parkinglot.spot.ParkingSpot;
import com.app.pblms.parkinglot.vehicle.Vehicle;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ParkingManager is responsible for managing multiple floors and
 * mapping vehicles to their assigned parking spots.
 */
public class ParkingManager {

    private final List<ParkingFloor> parkingFloors;
    private final Map<Vehicle, ParkingSpot> vehicleParkingSpotMap;

    public ParkingManager(List<ParkingFloor> parkingFloors) {
        this.parkingFloors = parkingFloors;
        this.vehicleParkingSpotMap = new HashMap<>();
    }

    /**
     * Finds an available parking spot for a given vehicle
     * by checking all floors sequentially.
     */
    public ParkingSpot findSpotForVehicle(Vehicle vehicle) {
        for (ParkingFloor parkingFloor : this.parkingFloors) {
            ParkingSpot parkingSpot = parkingFloor.findSpotForVehicle(vehicle);
            if (parkingSpot != null) {
                return parkingSpot;
            }
        }

        return null;
    }

    /**
     * Parks the vehicle in the first available spot found.
     * @return the spot assigned or null if no spot is available
     */
    public ParkingSpot parkVehicle(Vehicle vehicle) {
        ParkingSpot parkingSpot = this.findSpotForVehicle(vehicle);
        if (parkingSpot != null) {
            parkingSpot.occupy(vehicle);
            this.vehicleParkingSpotMap.put(vehicle, parkingSpot);
        }
        return parkingSpot;
    }

    /**
     * Unparks the given vehicle and frees up the corresponding spot.
     */
    public void unparkVehicle(Vehicle vehicle) {
        ParkingSpot parkingSpot = this.vehicleParkingSpotMap.remove(vehicle);
        if (parkingSpot != null) {
            parkingSpot.vacate();
        }
    }

    /**
     * Finds the parking spot where a vehicle is currently parked.
     */
    public ParkingSpot findVehicleSpot(Vehicle vehicle) {
        return this.vehicleParkingSpotMap.get(vehicle);
    }

    @Override
    public String toString() {
        return STR."ParkingManager{parkingFloors=\{this.parkingFloors.size()}, parkedVehicles=\{this.vehicleParkingSpotMap.size()}}";
    }

}
