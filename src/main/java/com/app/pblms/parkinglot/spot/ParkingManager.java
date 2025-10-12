package com.app.pblms.parkinglot.spot;

import com.app.pblms.parkinglot.vehicle.Vehicle;
import com.app.pblms.parkinglot.vehicle.VehicleSize;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingManager {
    private final Map<VehicleSize, List<ParkingSpot>> availableSpots;
    private final Map<Vehicle, ParkingSpot> vehicleParkingSpotMap;

    public ParkingManager(Map<VehicleSize, List<ParkingSpot>> availableSpots) {
        this.availableSpots = availableSpots;
        this.vehicleParkingSpotMap = new HashMap<>();
    }

    public ParkingSpot findSpotForVehicle(Vehicle vehicle) {
        VehicleSize vehicleSize = vehicle.getSize();

        for (VehicleSize size : VehicleSize.values()) {
            if (size.ordinal() < vehicleSize.ordinal()) continue;

            List<ParkingSpot> spots = availableSpots.get(size);
            for (ParkingSpot spot : spots) {
                if (spot.isAvailable()) {
                    return spot;
                }
            }
        }

        return null;
    }

    public ParkingSpot parkVehicle(Vehicle vehicle) {
        ParkingSpot spot = this.findSpotForVehicle(vehicle);
        if (spot == null) return null;

        spot.occupy(vehicle);
        vehicleParkingSpotMap.put(vehicle, spot);
        availableSpots.get(spot.getSize()).add(spot);


        return spot;
    }

    public void unparkVehicle(Vehicle vehicle) {
        ParkingSpot spot = this.vehicleParkingSpotMap.remove(vehicle);
        if (spot == null) return;

        spot.vacate();
        availableSpots.get(spot.getSize()).remove(spot);
    }

    public ParkingSpot findVehicleSpot(Vehicle vehicle) {
        return vehicleParkingSpotMap.get(vehicle);
    }
}
