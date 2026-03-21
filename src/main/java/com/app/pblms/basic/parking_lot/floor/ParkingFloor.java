package com.app.pblms.basic.parking_lot.floor;

import com.app.pblms.basic.parking_lot.spot.ParkingSpot;

import java.util.ArrayList;
import java.util.List;

public class ParkingFloor {

    private final int floorNumber;
    private final List<ParkingSpot> parkingSpots;

    public ParkingFloor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.parkingSpots = new ArrayList<>();
    }

    public void addSpot(ParkingSpot parkingSpot) {
        parkingSpots.add(parkingSpot);
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public List<ParkingSpot> getParkingSpots() {
        return parkingSpots;
    }
}
