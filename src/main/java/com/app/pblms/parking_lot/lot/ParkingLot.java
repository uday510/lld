package com.app.pblms.parking_lot.lot;

import com.app.pblms.parking_lot.fare.FareCalculator;
import com.app.pblms.parking_lot.floor.ParkingFloor;
import com.app.pblms.parking_lot.payment.Payment;
import com.app.pblms.parking_lot.payment.PaymentMethod;
import com.app.pblms.parking_lot.payment.PaymentService;
import com.app.pblms.parking_lot.spot.ParkingSpot;
import com.app.pblms.parking_lot.ticket.Ticket;
import com.app.pblms.parking_lot.vehicle.Vehicle;
import com.app.pblms.parking_lot.vehicle.VehicleSize;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ParkingLot {

    private final List<ParkingFloor> parkingFloors;
    private final PaymentService paymentService;
    private final FareCalculator fareCalculator;
    private final Map<VehicleSize, Queue<ParkingSpot>> freeSpots;
    private final Map<String, ParkingSpot> vehicleParkingSpotStore;
    private final VehicleSize[] vehicleSizes;

    public ParkingLot(
        List<ParkingFloor> parkingFloors,
        FareCalculator fareCalculator
    ) {

        this.parkingFloors = parkingFloors;
        this.fareCalculator = fareCalculator;
        this.paymentService = new PaymentService();
        this.freeSpots = new EnumMap<>(VehicleSize.class);
        vehicleParkingSpotStore = new ConcurrentHashMap<>();

        vehicleSizes = VehicleSize.values();
        for (VehicleSize vehicleSize : vehicleSizes) {
            freeSpots.put(vehicleSize, new ConcurrentLinkedQueue<>());
        }

        initializeFreeSpots();
    }

    public Ticket park(Vehicle vehicle) {
        String plate = vehicle.getLicensePlate();
        ParkingSpot parkingSpot = getParkingSpot(vehicle);

        if (parkingSpot == null) {
            throw new IllegalStateException("Parking Spot Unavailable for size " + vehicle.getVehicleSize());
        }

        ParkingSpot existing = vehicleParkingSpotStore.putIfAbsent(plate, parkingSpot);
        if (existing != null) {
            releaseSpot(parkingSpot);
            throw new IllegalStateException("Vehicle " + plate + " is already parked");
        }

        try {
            parkingSpot.occupy(vehicle);
        } catch (Exception e) {

            vehicleParkingSpotStore.remove(plate);
            releaseSpot(parkingSpot);
            throw e;
        }

        return new Ticket(vehicle, parkingSpot);
    }

    public Payment unpark(Ticket ticket, PaymentMethod paymentMethod) {
        String plate = ticket.getVehicle().getLicensePlate();

        ParkingSpot parkingSpot = vehicleParkingSpotStore.remove(plate);
        if (parkingSpot == null) {
            throw new IllegalStateException("No active parking for vehicle " + plate);
        }

        ticket.closeTicket();
        BigDecimal fare = fareCalculator.calculateFare(ticket);

        parkingSpot.vacate();
        releaseSpot(parkingSpot);

        return paymentService.pay(fare, paymentMethod);
    }

    private ParkingSpot getParkingSpot (Vehicle vehicle) {
        VehicleSize reqVehicleSize = vehicle.getVehicleSize();

        for (VehicleSize curVehicleSize : vehicleSizes) {

            if (curVehicleSize.compareTo(reqVehicleSize) < 0) continue;

            Queue<ParkingSpot> queue = freeSpots.get(curVehicleSize);

           for (ParkingSpot parkingSpot : queue) {
               if (parkingSpot.canFitVehicle(vehicle)) {

                   if (queue.remove(parkingSpot)) {
                       return parkingSpot;
                   }

               }
           }
        }

        return null;
    }

    private void releaseSpot(ParkingSpot parkingSpot) {
        freeSpots.get(parkingSpot.getVehicleSize()).offer(parkingSpot);
    }

    private void initializeFreeSpots() {

        for (ParkingFloor parkingFloor : parkingFloors) {
            for (ParkingSpot parkingSpot : parkingFloor.getParkingSpots()) {
                freeSpots.get(parkingSpot.getVehicleSize()).offer(parkingSpot);
            }
        }

    }

}