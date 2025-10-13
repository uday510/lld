package com.app.pblms.parkinglot;

import com.app.pblms.parkinglot.fare.FareCalculator;
import com.app.pblms.parkinglot.manager.ParkingManager;
import com.app.pblms.parkinglot.payment.Payment;
import com.app.pblms.parkinglot.payment.PaymentProcessor;
import com.app.pblms.parkinglot.spot.ParkingSpot;
import com.app.pblms.parkinglot.ticket.Ticket;
import com.app.pblms.parkinglot.vehicle.Vehicle;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * ParkingLot acts as the central controller
 * It handles vehicle entry, ticketing, exit, fare calculation and payment
 */
public class ParkingLot {

    private final ParkingManager parkingManager;
    private final FareCalculator fareCalculator;
    private final PaymentProcessor paymentProcessor;

    public ParkingLot(ParkingManager parkingManager, FareCalculator fareCalculator, PaymentProcessor paymentProcessor) {
        this.parkingManager = parkingManager;
        this.fareCalculator = fareCalculator;
        this.paymentProcessor = paymentProcessor;
    }

    /**
     * Handles vehicle entry and ticket generation
     */
    public Ticket enterVehicle(Vehicle vehicle) {
        ParkingSpot parkingSpot = parkingManager.parkVehicle(vehicle);
        if (parkingSpot == null) {
            System.out.println(STR."No available spots for \{vehicle.getLicensePlate()}");
            return null;
        }

        Ticket ticket = new Ticket(vehicle, parkingSpot, LocalDateTime.now());
        System.out.println(STR."Vehicle \{vehicle.getLicensePlate()} parked at spot \{parkingSpot.getSpotNumber()}");
        return ticket;
    }

    /**
     * Handles vehicle exit, fare calculation and payment
     */
    public void exitVehicle(Ticket ticket) {
        if (ticket == null) {
            throw new IllegalArgumentException("Ticket cannot be null.");
        }

        ticket.setExitTime(LocalDateTime.now());
        parkingManager.unparkVehicle(ticket.getVehicle());

        BigDecimal fare = fareCalculator.calculateFare(ticket);
        System.out.println(STR."Total Fare: \{fare} for vehilce \{ticket.getVehicle().getLicensePlate()}");

        Payment payment = paymentProcessor.process(fare);
        System.out.println("Payment processed: " + payment);
    }

}
