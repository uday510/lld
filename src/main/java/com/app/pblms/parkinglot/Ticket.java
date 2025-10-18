package com.app.pblms.parkinglot;

import com.app.pblms.parkinglot.spot.ParkingSpot;
import com.app.pblms.parkinglot.vehicle.Vehicle;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Random;

/**
 * Ticket represents a parking session for a specific vehicle.
 * It records entry and exit times, the assigned parking spot, and allows duration calculation.
 */
public class Ticket {

    private final String ticketId;
    private final Vehicle vehicle;
    private final ParkingSpot parkingSpot;
    private final LocalDateTime entryTime;
    private LocalDateTime exitTime;

    public Ticket(Vehicle vehicle, ParkingSpot parkingSpot, LocalDateTime entryTime) {
        this.ticketId = generateTicket();
        this.vehicle = vehicle;
        this.parkingSpot = parkingSpot;
        this.entryTime = entryTime;
    }

    private String generateTicket() {
        return STR."TICKET-\{System.currentTimeMillis() + ((int) (Math.random() * 1000)) }";
    }

    public Vehicle getVehicle() {
        return this.vehicle;
    }

    public ParkingSpot getParkingSpot() {
        return this.parkingSpot;
    }

    public LocalDateTime getEntryTime() {
        return this.entryTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    /**
     * Calculates the parking duration in minutes.
     * @return parking duration as BigDecimal
     */
    public BigDecimal calculateParkingDuration() {
        long minutes = Duration.between(entryTime, Objects.requireNonNullElse(exitTime, LocalDateTime.now())).toMinutes();
        return BigDecimal.valueOf(minutes);
    }

    @Override
    public String toString() {
        return STR."Ticket{ticketId='\{ticketId}', vehicle=\{vehicle.getLicensePlate()}, spot=\{parkingSpot.getSpotNumber()}, entryTime=\{entryTime}, exitTime=\{exitTime}}";
    }
}
