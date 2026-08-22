package com.app;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

enum VehicleSize {
    SMALL, MEDIUM, LARGE
}

interface Vehicle {
    public VehicleSize getVehicleSize();
    public String getLicensePlate();
}

abstract class AbstractVehicle {
    private final String licensePlate;

    public AbstractVehicle(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getLicensePlate() {
        return licensePlate;
    }
}

class Motorcycle extends AbstractVehicle {
    public Motorcycle(String licensePlate) {
        super(licensePlate);
    }
    public VehicleSize getVehicleSize() {
        return VehicleSize.SMALL;
    }
}

class Car extends AbstractVehicle {
    public Car(String licensePlate) {
        super(licensePlate);
    }

    public VehicleSize getVehicleSize() {
        return VehicleSize.MEDIUM;
    }
}

class Truck extends AbstractVehicle {
    public Truck(String licensePlate) {
        super(licensePlate);
    }
    public VehicleSize getVehicleSize() {
        return VehicleSize.LARGE;
    }
}

class ParkingSpot {
    private final String spotId;
    private final VehicleSize size;

    private Vehicle vehicle;

    public ParkingSpot(String spotId, VehicleSize size) {
        this.spotId = spotId;
        this.size = size;
    }

    public String getSpotId() {
        return spotId;
    }

    public VehicleSize getSize() {
        return size;
    }

    public boolean isAvailable() {
        return vehicle == null;
    }

    public boolean canFitVehicle() {
        return vehicle.getVehicleSize().ordinal() <= size.ordinal();
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

class ParkingFloor {
    private final String floorId;
    private final List<ParkingSpot> parkingSpots;

    public ParkingFloor (String floorId, List<ParkingSpot> parkingSpots) {
        this.floorId = floorId;
        this.parkingSpots = new CopyOnWriteArrayList<>(parkingSpots);
    }

    public List<ParkingSpot> getParkingSpots() {
        return parkingSpots;
    }

    public void addParkingSpot(ParkingSpot parkingSpot) {
        if (parkingSpot == null)
            throw new IllegalStateException("Parking Spot is null");
        parkingSpots.add(parkingSpot);
    }

}

