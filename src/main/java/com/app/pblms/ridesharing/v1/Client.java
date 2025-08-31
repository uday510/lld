package com.app.pblms.ridesharing.v1;

public class Client {

    public static void main(String[] args) {

        Location l1 = new Location(12.9716, 77.9125);
        Location l2 = new Location(9.9716, 57.9774);
        Location l3 = new Location(10.1287, 69.9512);


        Vehicle car = new Vehicle("ABC1235", "car");
        Vehicle bike = new Vehicle("ABC1304", "bike");
        Vehicle lorry = new Vehicle("ABC1240", "lorry");


        Driver driver1 = new Driver("driver1", car, l1);
        Driver driver2 = new Driver("driver2", bike, l2);
        Driver driver3 = new Driver("driver3", lorry, l3);


        Passenger passenger1 = new Passenger("alan turing", l1);
        Passenger passenger2 = new Passenger("albert einstein", l1);

        // Ride sharing

        RideSharingAppService app = new RideSharingAppService();

        app.addDriver(driver1);
        app.addDriver(driver2);
        app.addDriver(driver3);


        app.addPassenger(passenger1);
        app.addPassenger(passenger2);


        // Book the ride

        app.bookRide(passenger1, 10);
        app.bookRide(passenger2, 20);

    }

}
