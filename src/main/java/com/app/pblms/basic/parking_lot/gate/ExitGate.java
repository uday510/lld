package com.app.pblms.basic.parking_lot.gate;

import com.app.pblms.basic.parking_lot.lot.ParkingLot;
import com.app.pblms.basic.parking_lot.payment.Payment;
import com.app.pblms.basic.parking_lot.payment.PaymentProcessor;
import com.app.pblms.basic.parking_lot.ticket.Ticket;

import java.math.BigDecimal;

public class ExitGate {

    private final int gateId;
    private final ParkingLot parkingLot;
    private final PaymentProcessor paymentProcessor;


    public ExitGate(int gateId,
                    ParkingLot parkingLot,
                    PaymentProcessor paymentProcessor) {

        this.gateId = gateId;
        this.parkingLot = parkingLot;
        this.paymentProcessor = paymentProcessor;
    }

    public Payment exit(Ticket ticket) {

        BigDecimal fare = parkingLot.unpark(ticket);

        return paymentProcessor.process(fare);
    }
}
