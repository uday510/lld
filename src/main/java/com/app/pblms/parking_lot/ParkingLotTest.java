package com.app.pblms.parking_lot;

import com.app.pblms.parking_lot.fare.BaseFareStrategy;
import com.app.pblms.parking_lot.fare.FareCalculator;
import com.app.pblms.parking_lot.fare.PeakHourFareStrategy;
import com.app.pblms.parking_lot.floor.ParkingFloor;
import com.app.pblms.parking_lot.gate.EntryGate;
import com.app.pblms.parking_lot.gate.ExitGate;
import com.app.pblms.parking_lot.lot.ParkingLot;
import com.app.pblms.parking_lot.payment.Payment;
import com.app.pblms.parking_lot.spot.ParkingSpot;
import com.app.pblms.parking_lot.ticket.Ticket;
import com.app.pblms.parking_lot.vehicle.Car;
import com.app.pblms.parking_lot.vehicle.Vehicle;
import com.app.pblms.parking_lot.vehicle.VehicleSize;

import com.app.pblms.parking_lot.payment.PaymentMethod;
import com.app.pblms.parking_lot.vehicle.Motorcycle;
import com.app.pblms.parking_lot.vehicle.Truck;

import java.util.List;

public class ParkingLotTest {

    static void main(String[] args) {
        System.out.println("=== INITIALIZING PARKING LOT SYSTEM ===");

        // 1. Setup Floors and Spots (Capacity: 2 Small, 2 Medium, 2 Large)
        ParkingFloor parkingFloor1 = new ParkingFloor(1);
        parkingFloor1.addParkingSpots(List.of(
                new ParkingSpot(100, VehicleSize.SMALL),
                new ParkingSpot(101, VehicleSize.MEDIUM),
                new ParkingSpot(102, VehicleSize.LARGE)
        ));

        ParkingFloor parkingFloor2 = new ParkingFloor(2);
        parkingFloor2.addParkingSpots(List.of(
                new ParkingSpot(200, VehicleSize.SMALL),
                new ParkingSpot(201, VehicleSize.MEDIUM),
                new ParkingSpot(202, VehicleSize.LARGE)
        ));

        // 2. Setup Strategies and Calculator
        FareCalculator fareCalculator = new FareCalculator(
                List.of(new BaseFareStrategy(), new PeakHourFareStrategy()
        ));

        // 3. Initialize Parking Lot
        ParkingLot parkingLot = new ParkingLot(List.of(parkingFloor1, parkingFloor2), fareCalculator);

        // 4. Setup Gates
        EntryGate entryGate1 = new EntryGate(1, parkingLot);
        EntryGate entryGate2 = new EntryGate(2, parkingLot);
        
        ExitGate exitGate1 = new ExitGate(1, parkingLot);
        ExitGate exitGate2 = new ExitGate(2, parkingLot);

        // 5. Create Vehicles
        Vehicle motorcycle1 = new Motorcycle("MOTO-111");
        Vehicle motorcycle2 = new Motorcycle("MOTO-222");
        Vehicle motorcycle3 = new Motorcycle("MOTO-333"); // Used to test overflow
        Vehicle car1 = new Car("CAR-111");
        Vehicle car2 = new Car("CAR-222");
        Vehicle truck1 = new Truck("TRK-111");

        System.out.println("Initialization Complete.\n");

        // ==========================================
        // TEST CASE 1: Standard Parking & Unparking
        // ==========================================
        System.out.println("--- TEST CASE 1: Standard Flow ---");
        try {
            Ticket t1 = entryGate1.enter(motorcycle1);
            System.out.println("Parked Motorcycle in spot: " + t1.getParkingSpot().getSpotNumber());

            Ticket t2 = entryGate2.enter(car1);
            System.out.println("Parked Car in spot: " + t2.getParkingSpot().getSpotNumber());

            // Unpark and pay
            Payment p1 = exitGate1.exit(t1, PaymentMethod.CASH);
            System.out.println("Motorcycle exited. Paid: $" + p1.getAmount() + " via " + p1.getPaymentMethod());

            Payment p2 = exitGate2.exit(t2, PaymentMethod.CREDIT_CARD);
            System.out.println("Car exited. Paid: $" + p2.getAmount() + " via " + p2.getPaymentMethod());
            
            System.out.println("Test Case 1 Passed.\n");
        } catch (Exception e) {
            System.out.println("Test Case 1 Failed: " + e.getMessage());
        }

        // ==========================================
        // TEST CASE 2: Duplicate Vehicle Prevention
        // ==========================================
        System.out.println("--- TEST CASE 2: Duplicate Vehicle ---");
        try {
            Ticket t3 = entryGate1.enter(truck1);
            System.out.println("Parked Truck in spot: " + t3.getParkingSpot().getSpotNumber());
            
            // Try to park the exact same truck again
            System.out.println("Attempting to park the same Truck again...");
            entryGate2.enter(truck1); 
        } catch (Exception e) {
            System.out.println("Caught expected exception: " + e.getMessage());
            System.out.println("Test Case 2 Passed.\n");
        }

        // ==========================================
        // TEST CASE 3: Vehicle Upgrades Spot (Small in Medium Spot)
        // ==========================================
        System.out.println("--- TEST CASE 3: Spot Upgrade ---");
        try {
            // Capacity is 2 small spots. Let's fill them.
            entryGate1.enter(motorcycle1); // Fills Small Spot 1 (Floor 1)
            entryGate1.enter(motorcycle2); // Fills Small Spot 2 (Floor 2)
            
            // Try to park a 3rd motorcycle. 
            // Based on your logic: vehicleSize.ordinal() <= parkingSpotSize.ordinal()
            // It should grab a MEDIUM spot!
            Ticket overflowTicket = entryGate2.enter(motorcycle3);
            System.out.println("3rd Motorcycle parked in spot: " + overflowTicket.getParkingSpot().getSpotNumber() 
                    + " (Size: " + overflowTicket.getParkingSpot().getVehicleSize() + ")");
            System.out.println("Test Case 3 Passed.\n");
        } catch (Exception e) {
            System.out.println("Test Case 3 Failed: " + e.getMessage());
        }

        // ==========================================
        // TEST CASE 4: Lot Full / Unavailability
        // ==========================================
        System.out.println("--- TEST CASE 4: Lot Full / No Spots ---");
        try {
            // Currently Parked: Truck1(Large), Moto1(Small), Moto2(Small), Moto3(Medium)
            // Remaining: 1 Medium, 1 Large.
            
            entryGate1.enter(car2); // Takes the last Medium spot
            
            Vehicle truck2 = new Truck("TRK-222");
            entryGate2.enter(truck2); // Takes the last Large spot
            
            // Lot is effectively full for Cars and Trucks now (Only a small vehicle could technically steal a large spot, but let's try a Truck)
            System.out.println("Attempting to park a 3rd Truck when no Large spots are left...");
            Vehicle truck3 = new Truck("TRK-333");
            entryGate1.enter(truck3); 
            
        } catch (IllegalStateException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
            System.out.println("Test Case 4 Passed.\n");
        }
        
        System.out.println("=== TESTING COMPLETE ===");
    }
}