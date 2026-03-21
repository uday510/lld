package com.app.pblms.basic.parking_lot.ticket;

import com.app.pblms.basic.parking_lot.spot.ParkingSpot;
import com.app.pblms.basic.parking_lot.vehicle.Vehicle;

import java.time.Duration;
import java.time.LocalDateTime;

public class Ticket {

    private final Vehicle vehicle;
    private final ParkingSpot parkingSpot;
    private final LocalDateTime entryTime;

    private LocalDateTime exitTime;

    public Ticket(Vehicle vehicle, ParkingSpot parkingSpot) {
        this.vehicle = vehicle;
        this.parkingSpot = parkingSpot;
        this.entryTime = LocalDateTime.now();
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void closeTicket() {
        this.exitTime = LocalDateTime.now();
    }

    public long getParkingDurationMinutes() {

        LocalDateTime endTime = (exitTime == null)
                ? LocalDateTime.now()
                : exitTime;

        long minutes = Duration.between(entryTime, endTime).toMinutes();

        return Math.max(minutes, 1);
    }
}
