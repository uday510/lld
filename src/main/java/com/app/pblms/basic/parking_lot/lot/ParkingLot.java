package com.app.pblms.basic.parking_lot.lot;

import com.app.pblms.basic.parking_lot.fare.FareCalculator;
import com.app.pblms.basic.parking_lot.floor.ParkingFloor;
import com.app.pblms.basic.parking_lot.spot.ParkingSpot;
import com.app.pblms.basic.parking_lot.ticket.Ticket;
import com.app.pblms.basic.parking_lot.vehicle.Vehicle;
import com.app.pblms.basic.parking_lot.vehicle.VehicleSize;

import java.math.BigDecimal;
import java.util.*;

public class ParkingLot {

    private final List<ParkingFloor> parkingFloors;
    private final Map<VehicleSize, Queue<ParkingSpot>> freeSpots;
    private final FareCalculator fareCalculator;

    public ParkingLot(List<ParkingFloor> parkingFloors,
                      FareCalculator fareCalculator) {

        this.parkingFloors = parkingFloors;
        this.fareCalculator = fareCalculator;
        this.freeSpots = new EnumMap<>(VehicleSize.class);

        for (VehicleSize size : VehicleSize.values()) {
            freeSpots.put(size, new ArrayDeque<>());
        }

        initializeFreeSpots();

    }

    private void initializeFreeSpots() {

        for (ParkingFloor floor : parkingFloors) {

            for (ParkingSpot spot : floor.getParkingSpots()) {

                freeSpots.get(spot.getSize()).offer(spot);
            }
        }
    }

    public Ticket park(Vehicle vehicle) {

        ParkingSpot spot = allocateSpot(vehicle);

        if (spot == null) {
            throw new RuntimeException("Parking Full");
        }

        spot.occupy(vehicle);

        return new Ticket(vehicle, spot);
    }

    private ParkingSpot allocateSpot(Vehicle vehicle) {

        VehicleSize vehicleSize = vehicle.getSize();

        for (VehicleSize size : VehicleSize.values()) {

            if (size.compareTo(vehicleSize) < 0) continue;

            Queue<ParkingSpot> queue = freeSpots.get(size);

            ParkingSpot spot = queue.poll();

            if (spot != null && spot.canFitVehicle(vehicle)) {
                return spot;
            }
        }

        return null;
    }

    public BigDecimal unpark(Ticket ticket) {

        ticket.closeTicket();

        ParkingSpot spot = ticket.getParkingSpot();

        spot.vacate();

        freeSpots.get(spot.getSize()).offer(spot);

        return fareCalculator.calculateFare(ticket);
    }
}
