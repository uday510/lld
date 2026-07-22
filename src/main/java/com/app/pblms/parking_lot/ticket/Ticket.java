package com.app.pblms.parking_lot.ticket;

import com.app.pblms.parking_lot.spot.ParkingSpot;
import com.app.pblms.parking_lot.vehicle.Vehicle;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;

public class Ticket {

    private final Vehicle vehicle;
    private final ParkingSpot parkingSpot;
    private final Instant entryTime;

    private Instant exitTime;

    public Ticket(Vehicle vehicle, ParkingSpot parkingSpot) {
        this.vehicle = vehicle;
        this.parkingSpot = parkingSpot;
        this.entryTime = Instant.now();
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public Instant getEntryTime() {
        return entryTime;
    }

    public Instant getExitTime() {
        return exitTime;
    }

    public void closeTicket() {
        this.exitTime = Instant.now();
    }

    public long getParkingDurationMinutes() {
        ZoneId zoneId = ZoneId.systemDefault();
        Instant endTime = (exitTime == null)
                ? Instant.now()
                : exitTime;

        long minutes = Duration.between(entryTime, endTime).toMinutes();

        return Math.max(minutes, 1);
    }

}
