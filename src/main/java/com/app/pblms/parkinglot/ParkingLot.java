package com.app.pblms.parkinglot;

import com.app.pblms.parkinglot.fare.FareCalculator;
import com.app.pblms.parkinglot.payment.Payment;
import com.app.pblms.parkinglot.payment.PaymentMethod;
import com.app.pblms.parkinglot.payment.PaymentProcessor;
import com.app.pblms.parkinglot.spot.ParkingSpot;
import com.app.pblms.parkinglot.vehicle.Vehicle;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ParkingLot acts as the central controller.
 * It handles vehicle entry, ticketing, exit, fare calculation, and payment.
 */
public class ParkingLot {

    private final List<ParkingFloor> parkingFloors;
    private final FareCalculator fareCalculator;
    private final Map<PaymentMethod, PaymentProcessor> paymentProcessorMap;
    private final Map<Vehicle, ParkingSpot> vehicleParkingSpotMap;

    public ParkingLot(List<ParkingFloor> parkingFloors,
                      FareCalculator fareCalculator,
                      Map<PaymentMethod, PaymentProcessor> paymentProcessorMap) {
        this.parkingFloors = parkingFloors;
        this.fareCalculator = fareCalculator;
        this.paymentProcessorMap = paymentProcessorMap;
        this.vehicleParkingSpotMap = new HashMap<>();
    }

    /**
     * Handles vehicle entry and ticket generation.
     */
    public synchronized Ticket park(Vehicle vehicle) {
        ParkingSpot parkingSpot = findSpotForVehicle(vehicle);
        if (parkingSpot == null) {
            System.out.println("No available spots for vehicle " + vehicle.getLicensePlate());
            return null;
        }

        parkingSpot.occupy(vehicle);
        vehicleParkingSpotMap.put(vehicle, parkingSpot);

        Ticket ticket = new Ticket(vehicle, parkingSpot, LocalDateTime.now());
        System.out.println("Vehicle " + vehicle.getLicensePlate() +
                " parked at spot " + parkingSpot.getSpotNumber());
        return ticket;
    }

    /**
     * Handles vehicle exit, fare calculation, and payment.
     */
    public synchronized Payment unpark(Ticket ticket, PaymentMethod paymentMethod) {
        if (ticket == null) {
            throw new IllegalArgumentException("Ticket cannot be null");
        }

        ticket.setExitTime(LocalDateTime.now());

        ParkingSpot parkingSpot = vehicleParkingSpotMap.remove(ticket.getVehicle());
        if (parkingSpot == null) {
            throw new IllegalArgumentException("Parking spot not found for vehicle: " +
                    ticket.getVehicle().getLicensePlate());
        }

        parkingSpot.vacate();
        BigDecimal fare = fareCalculator.calculateFare(ticket);
        System.out.println("Total Fare: " + fare +
                " for vehicle " + ticket.getVehicle().getLicensePlate());

        PaymentProcessor paymentProcessor = paymentProcessorMap.get(paymentMethod);
        if (paymentProcessor == null) {
            throw new IllegalArgumentException("Unsupported payment method: " + paymentMethod);
        }

        Payment payment = paymentProcessor.process(fare);
        System.out.println("Payment processed successfully: " + payment);
        return payment;
    }

    /**
     * Finds an available parking spot for a given vehicle
     * by checking all floors sequentially.
     */
    private ParkingSpot findSpotForVehicle(Vehicle vehicle) {
        for (ParkingFloor floor : parkingFloors) {
            ParkingSpot parkingSpot = floor.findSpotForVehicle(vehicle);
            if (parkingSpot != null) {
                System.out.println("Found spot for " + vehicle.getLicensePlate() +
                        " at floor " + floor.getFloorNumber());
                return parkingSpot;
            }
        }
        return null;
    }
}