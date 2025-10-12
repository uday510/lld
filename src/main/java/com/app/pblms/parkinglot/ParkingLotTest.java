package com.app.pblms.parkinglot;

import com.app.pblms.parkinglot.fare.*;
import com.app.pblms.parkinglot.spot.ParkingManager;
import com.app.pblms.parkinglot.spot.ParkingSpot;
import com.app.pblms.parkinglot.spot.RegularSpot;
import com.app.pblms.parkinglot.vehicle.Car;
import com.app.pblms.parkinglot.vehicle.Vehicle;
import com.app.pblms.parkinglot.vehicle.VehicleSize;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParkingLotTest {

    @Test
    public void testVehicleJourney() {
        System.out.println("\n=== Parking Lot System: Full Vehicle Journey Test ===");

        // Setup
        Map<VehicleSize, List<ParkingSpot>> availableSpots = new HashMap<>();
        availableSpots.put(VehicleSize.MEDIUM, new ArrayList<>(List.of(new RegularSpot(1), new RegularSpot(2))));
        ParkingManager parkingManager = new ParkingManager(availableSpots);
        FareCalculator fareCalculator = new FareCalculator(List.of(new BaseFareStrategy(), new PeakHoursFareStrategy()));
        ParkingLot parkingLot = new ParkingLot(parkingManager, fareCalculator);

        Vehicle car = new Car("ABC123");
        Ticket ticket = parkingLot.enterVehicle(car);

        assertNotNull(ticket);
        assertNotNull(ticket.getParkingSpot());
        assertEquals(car, ticket.getVehicle());

        ParkingSpot foundSpot = parkingManager.findVehicleSpot(car);
        assertEquals(ticket.getParkingSpot(), foundSpot);

        BigDecimal fare = parkingLot.exitVehicle(ticket);
//        assertNotNull(fare);
//        assertTrue(fare.compareTo(BigDecimal.ZERO) > 0);
        System.out.println(fare);

        assertTrue(foundSpot.isAvailable());
        System.out.println("✓ Vehicle exit verified. Fare: " + fare);
    }
}
