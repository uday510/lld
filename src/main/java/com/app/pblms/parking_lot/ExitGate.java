package com.app.pblms.parking_lot;

import com.app.pblms.parking_lot.payment.Payment;
import com.app.pblms.parking_lot.payment.PaymentMethod;

public class ExitGate {

    private final int gateId;
    private final ParkingLot parkingLot;

    public ExitGate(int gateId, ParkingLot parkingLot) {
        this.gateId = gateId;
        this.parkingLot = parkingLot;
    }

    public int getGateId() {
        return gateId;
    }

    public Payment exit(Ticket ticket, PaymentMethod paymentMethod) {
        return parkingLot.unpark(ticket, paymentMethod);
    }

}
