package com.app.pblms.ridesharing.v1;

import java.util.ArrayList;
import java.util.List;

public class RideSharingAppService {

    // Matching Service
    private final List<Driver> drivers = new ArrayList<>();
    private final List<Passenger> passengers = new ArrayList<>();

    public void addDriver(Driver driver) {
        drivers.add(driver);
    }

    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
    }

    //Booking Ride
    public void bookRide(Passenger passenger, double distance) {
        // corner case

        if (drivers.isEmpty()) {
            System.out.println("No drivers available at the moment");
            return;
        }

        // Hard-coded assignment logic
        // find the nearest driver

        Driver assignedDriver = null;
        double minDistance = Double.MAX_VALUE;

        for (Driver driver : drivers) {
            double currentDriverDistance = calcDistance(passenger.location, driver.getLocation());
            if (currentDriverDistance < minDistance) {
                currentDriverDistance = minDistance;
                assignedDriver = driver;
            }
        }
        // Fare calculation
        assert assignedDriver != null;
        double expectedFare = calcFare(assignedDriver.getVehicle(), distance);


        System.out.println(String.format(
                "Ride booked for %s, Expected fare %.2f, Driver Details: %s",
                passenger.name, expectedFare, assignedDriver.toString()
        ));

        System.out.println("Driver arriving...");

    }

    private double calcDistance(Location l1, Location l2) {
        // use Euclidean distance
        double dx = l1.getLatitude() - l2.getLatitude();
        double dy = l1.getLongitude() - l2.getLongitude();

        return Math.sqrt((dx * dx) + (dy * dy));
    }

    private double calcFare(Vehicle vehicle, double distance) {

        if (vehicle.type.equalsIgnoreCase("car")) {
                return distance * 20;
        }

        if (vehicle.type.equals("bike")) {
            return distance * 10;
        }

        return distance * 8;
    }

}
