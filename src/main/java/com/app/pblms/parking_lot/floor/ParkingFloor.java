package com.app.pblms.parking_lot.floor;

import com.app.pblms.parking_lot.spot.ParkingSpot;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ParkingFloor {

    private final int floorNumber;
    private final List<ParkingSpot> parkingSpots;

    public ParkingFloor (int floorNumber) {
        this.floorNumber = floorNumber;
        this.parkingSpots = new CopyOnWriteArrayList<>();
    }

    public int getFloorNumber() {
        return this.floorNumber;
    }

    public List<ParkingSpot> getParkingSpots() {
        return parkingSpots;
    }

    public void addParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpots.add(parkingSpot);
    }

    public void addParkingSpots(List<ParkingSpot> parkingSpots) {
        this.parkingSpots.addAll(parkingSpots);
    }

 }
