package com.app.pblms.parking_lot;
import com.app.pblms.parking_lot.fare.BaseFareStrategy;
import com.app.pblms.parking_lot.fare.FareCalculator;
import com.app.pblms.parking_lot.fare.PeakHourFareStrategy;
import com.app.pblms.parking_lot.floor.ParkingFloor;
import com.app.pblms.parking_lot.gate.EntryGate;
import com.app.pblms.parking_lot.gate.ExitGate;
import com.app.pblms.parking_lot.lot.ParkingLot;
import com.app.pblms.parking_lot.payment.CashPaymentProcessor;
import com.app.pblms.parking_lot.payment.Payment;
import com.app.pblms.parking_lot.spot.ParkingSpot;
import com.app.pblms.parking_lot.ticket.Ticket;
import com.app.pblms.parking_lot.vehicle.Car;
import com.app.pblms.parking_lot.vehicle.Vehicle;
import com.app.pblms.parking_lot.vehicle.VehicleSize;

import java.util.List;

public class ParkingLotTest {

    static void main() throws InterruptedException {

        ParkingFloor floor1 = new ParkingFloor(1);

        floor1.addSpot(new ParkingSpot(1, VehicleSize.SMALL));
        floor1.addSpot(new ParkingSpot(2, VehicleSize.MEDIUM));
        floor1.addSpot(new ParkingSpot(3, VehicleSize.LARGE));

        FareCalculator calculator = new FareCalculator(
          List.of(
                  new BaseFareStrategy(),
                  new PeakHourFareStrategy()
          )
        );

        ParkingLot parkingLot =
                new ParkingLot(List.of(floor1), calculator);

        EntryGate entryGate = new EntryGate(1, parkingLot);
        ExitGate exitGate = new ExitGate(1, parkingLot, new CashPaymentProcessor());

        Vehicle car = new Car("KA-01-1234");

        System.out.println("Vehicle entering parking lot...");

        Ticket ticket = entryGate.enter(car);

        System.out.println("Ticket generated for vehicle: "
                + car.getLicensePlate());

        Thread.sleep(2000);

        System.out.println("Vehicle exiting parking lot...");

        Payment payment = exitGate.exit(ticket);

        System.out.println("Payment processed: " + payment.getAmount());

    }
}
