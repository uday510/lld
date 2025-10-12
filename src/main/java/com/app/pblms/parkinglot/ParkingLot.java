package com.app.pblms.parkinglot;

import com.app.pblms.parkinglot.fare.FareCalculator;
import com.app.pblms.parkinglot.fare.Ticket;
import com.app.pblms.parkinglot.spot.ParkingManager;
import com.app.pblms.parkinglot.spot.ParkingSpot;
import com.app.pblms.parkinglot.vehicle.Vehicle;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ParkingLot {

    private final ParkingManager parkingManager;
    private final FareCalculator fareCalculator;

    public ParkingLot(ParkingManager parkingManager, FareCalculator fareCalculator) {
        this.parkingManager = parkingManager;
        this.fareCalculator = fareCalculator;
    }


    public Ticket enterVehicle(Vehicle vehicle) {
        ParkingSpot parkingSpot = parkingManager.parkVehicle(vehicle);

        if (parkingSpot == null) return null;

        Ticket ticket = new Ticket(generateTicketId(), vehicle, parkingSpot, LocalDateTime.now());
        return ticket;
    }

    public BigDecimal exitVehicle(Ticket ticket) {
        if (ticket == null || ticket.getVehicle() == null) return null;

        ticket.setExitTime(LocalDateTime.now());
        parkingManager.unparkVehicle(ticket.getVehicle());

        BigDecimal fare = fareCalculator.calculateFare(ticket);
        return fare;
    }

    private String generateTicketId() {
        return "TICKET-" + System.currentTimeMillis();
    }
}
