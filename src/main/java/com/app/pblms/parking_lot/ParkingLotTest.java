package com.app.pblms.parking_lot;

import com.app.pblms.parking_lot.fare.BaseFareStrategy;
import com.app.pblms.parking_lot.fare.FareCalculator;
import com.app.pblms.parking_lot.fare.FareStrategy;
import com.app.pblms.parking_lot.fare.PeakHourFareStrategy;
import com.app.pblms.parking_lot.payment.*;
import com.app.pblms.parking_lot.spot.*;
import com.app.pblms.parking_lot.vehicle.Car;
import com.app.pblms.parking_lot.vehicle.Motorcycle;
import com.app.pblms.parking_lot.vehicle.Truck;
import com.app.pblms.parking_lot.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLotTest {

    static void main(String[] args) {

        System.out.println("\n=== Parking Lot System ===\n");

        // Create parking spots for two floors
        ParkingSpot p1 = new OversizedSpot(101);
        ParkingSpot p2 = new HandicappedSpot(102);
        ParkingFloor floor1 = new ParkingFloor(1);
        floor1.addSpot(p1);
        floor1.addSpot(p2);

        ParkingSpot p3 = new RegularSpot(201);
        ParkingSpot p4 = new OversizedSpot(202);
        ParkingFloor floor2 = new ParkingFloor(2);
        floor2.addSpot(p3);
        floor2.addSpot(p4);

        // Combine floors
        List<ParkingFloor> parkingFloors = new ArrayList<>();
        parkingFloors.add(floor1);
        parkingFloors.add(floor2);

        // Set up fare strategies
        List<FareStrategy> fareStrategies = new ArrayList<>();
        fareStrategies.add(new BaseFareStrategy());
        fareStrategies.add(new PeakHourFareStrategy());
        FareCalculator fareCalculator = new FareCalculator(fareStrategies);

        // Register payment processors
        Map<PaymentMethod, PaymentProcessor> paymentProcessorMap = new HashMap<>();
        paymentProcessorMap.put(PaymentMethod.CASH, new CashPaymentProcessor());
        paymentProcessorMap.put(PaymentMethod.CREDIT_CARD, new CreditCardPaymentProcessor());

        // Create parking lot instance
        ParkingLot parkingLot = new ParkingLot(parkingFloors, fareCalculator, paymentProcessorMap);

        // Create gates
        EntranceGate entranceGate1 = new EntranceGate(1, parkingLot);
        EntranceGate entranceGate2 = new EntranceGate(2, parkingLot);
        ExitGate exitGate1 = new ExitGate(3, parkingLot);
        ExitGate exitGate2 = new ExitGate(4, parkingLot);

        // Create vehicles
        Vehicle bike = new Motorcycle("AP39-AZ1010");
        Vehicle car = new Car("AP39-AZ1011");
        Vehicle truck = new Truck("AP39-AZ1012");

        System.out.println("\n--- Vehicle Entry Phase ---");
        Ticket bikeTicket = entranceGate1.enter(bike);
        Ticket carTicket = entranceGate2.enter(car);
        Ticket truckTicket = entranceGate2.enter(truck);

        System.out.println("\n--- Vehicle Exit Phase ---");
        if (bikeTicket != null) {
            exitGate1.exit(bikeTicket, PaymentMethod.CASH);
        }
        if (carTicket != null) {
            exitGate2.exit(carTicket, PaymentMethod.CREDIT_CARD);
        }
        if (truckTicket != null) {
            exitGate2.exit(truckTicket, PaymentMethod.CREDIT_CARD);
        }

        System.out.println("\n === Completed ===\n");
    }
}