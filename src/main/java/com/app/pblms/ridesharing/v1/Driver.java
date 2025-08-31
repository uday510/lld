package com.app.pblms.ridesharing.v1;

public class Driver {

    private String name;
    private Vehicle vehicle;
    private Location location;

    public Driver(String name, Vehicle vehicle, Location location) {
        this.name = name;
        this.vehicle = vehicle;
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "name='" + name + '\'' +
                ", vehicle=" + vehicle +
                ", location=" + location +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public String getName() {
        return name;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}
