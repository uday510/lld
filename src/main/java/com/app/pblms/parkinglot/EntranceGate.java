package com.app.pblms.parkinglot;

import com.app.pblms.parkinglot.vehicle.Vehicle;

public class EntranceGate {

    private final int gateId;
    private final ParkingLot parkingLot;

    public EntranceGate(int gateId, ParkingLot parkingLot) {
        this.gateId = gateId;
        this.parkingLot = parkingLot;
    }

    public int getGateId() {
        return gateId;
    }

    public Ticket enter(Vehicle vehicle) {
        return parkingLot.park(vehicle);
    }

}
