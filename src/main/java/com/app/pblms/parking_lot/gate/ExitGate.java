package com.app.pblms.parking_lot.gate;

import com.app.oops.paymentservice.PaymentMethod;
import com.app.pblms.parking_lot.lot.ParkingLot;
import com.app.pblms.parking_lot.payment.Payment;
import com.app.pblms.parking_lot.payment.PaymentProcessor;
import com.app.pblms.parking_lot.payment.PaymentProcessorFactory;
import com.app.pblms.parking_lot.ticket.Ticket;

import java.math.BigDecimal;

public class ExitGate {

    private final int gateId;
    private final ParkingLot parkingLot;
    private final PaymentProcessorFactory paymentProcessorFactory;


    public ExitGate(int gateId,
                    ParkingLot parkingLot,
                    PaymentProcessorFactory paymentProcessorFactory) {

        this.gateId = gateId;
        this.parkingLot = parkingLot;
        this.paymentProcessorFactory = paymentProcessorFactory;
    }

    public Payment exit(Ticket ticket, PaymentMethod paymentMethod) {

        BigDecimal fare = parkingLot.unpark(ticket);

        PaymentProcessor paymentProcessor = paymentProcessorFactory.getProcessor(paymentMethod);

        return paymentProcessor.process(fare);
    }

}
