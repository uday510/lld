package com.app.pblms.parkinglot;

import com.app.pblms.parkinglot.fare.BaseFareStrategy;
import com.app.pblms.parkinglot.fare.FareCalculator;
import com.app.pblms.parkinglot.fare.FareStrategy;
import com.app.pblms.parkinglot.fare.PeakHoursFareStrategy;
import com.app.pblms.parkinglot.floor.ParkingFloor;
import com.app.pblms.parkinglot.manager.ParkingManager;
import com.app.pblms.parkinglot.payment.CashPaymentProcessor;
import com.app.pblms.parkinglot.payment.PaymentProcessor;
import com.app.pblms.parkinglot.spot.ParkingSpot;
import com.app.pblms.parkinglot.spot.ParkingSpotImpl;
import com.app.pblms.parkinglot.ticket.Ticket;
import com.app.pblms.parkinglot.vehicle.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

    static void main() throws InterruptedException {
        System.out.println("\n=== Parking Lot System ===\n");

        // Floor 1 (Large spots for trucks)
        ParkingSpot t1 = new ParkingSpotImpl(101, VehicleSize.LARGE);
        ParkingSpot t2 = new ParkingSpotImpl(102, VehicleSize.LARGE);
        ParkingFloor floor1 = new ParkingFloor(1);
        floor1.addSpot(t1);
        floor1.addSpot(t2);

        // Floor 2 (Medium spots for cars)
        ParkingSpot c1 = new ParkingSpotImpl(201, VehicleSize.MEDIUM);
        ParkingSpot c2 = new ParkingSpotImpl(202, VehicleSize.MEDIUM);
        ParkingFloor floor2 = new ParkingFloor(2);
        floor2.addSpot(c1);
        floor2.addSpot(c2);

        // Floor 3 (Small spots for motorcycles)
        ParkingSpot m1 = new ParkingSpotImpl(301, VehicleSize.SMALL);
        ParkingSpot m2 = new ParkingSpotImpl(302, VehicleSize.SMALL);
        ParkingFloor floor3 = new ParkingFloor(3);
        floor3.addSpot(m1);
        floor3.addSpot(m2);

        List<ParkingFloor> floors = new ArrayList<>();
        floors.add(floor1);
        floors.add(floor2);
        floors.add(floor3);

        ParkingManager parkingManager = new ParkingManager(floors);

        List<FareStrategy> fareStrategies = List.of(
                new BaseFareStrategy(),
                new PeakHoursFareStrategy()
        );

        FareCalculator fareCalculator = new FareCalculator(fareStrategies);

        PaymentProcessor paymentProcessor = new CashPaymentProcessor();

        ParkingLot parkingLot = new ParkingLot(parkingManager, fareCalculator, paymentProcessor);

        Vehicle truck = new Truck("TS-07-TR-9001");
        Vehicle car = new Car("AP-39-AZ-1234");
        Vehicle motorcycle = new Motorcycle("KA-12-KC-7777");

        System.out.println("\n Parking Vehicles...");
        Ticket truckTicket = parkingLot.enterVehicle(truck);
        Ticket carTicket = parkingLot.enterVehicle(car);
        Ticket bikeTicket = parkingLot.enterVehicle(motorcycle);

        System.out.println("\n Tickets issued:");
        System.out.println(truckTicket);
        System.out.println(carTicket);
        System.out.println(bikeTicket);

        System.out.println("\n Vehicles are parked... (simulating 3 seconds)\n");
        Thread.sleep(3000);

        System.out.println("\n Vehicles exiting...");
        parkingLot.exitVehicle(truckTicket);
        parkingLot.exitVehicle(carTicket);
        parkingLot.exitVehicle(bikeTicket);

        System.out.println("\n All vehicles exited successfully!");
        System.out.println("=== Completed ===");
    }
}
