package com.app.pblms.basic.parking_lot.gate;
import com.app.pblms.basic.parking_lot.lot.ParkingLot;
import com.app.pblms.basic.parking_lot.ticket.Ticket;
import com.app.pblms.basic.parking_lot.vehicle.Vehicle;

public class EntryGate {

    private final int gateId;
    private final ParkingLot parkingLot;

    public EntryGate(int gateId, ParkingLot parkingLot) {
        this.gateId = gateId;
        this.parkingLot = parkingLot;
    }

    public Ticket enter(Vehicle vehicle) {

        return parkingLot.park(vehicle);
    }
}
