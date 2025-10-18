package com.app.pblms.parking_lot;

import com.app.pblms.parking_lot.vehicle.Vehicle;

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
