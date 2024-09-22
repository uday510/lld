package com.app.oops.inheritance;

// Base class (Parent)
public class Vehicle {
    private final String name;
    private final String model;
    Vehicle(String name, String model) {
        this.name = name;
        this.model = model;
    }
    public void getName() {
        System.out.println("The car is a " + name + " " + model);
    }
}

// Single inheritance
// FuelCar class extending from Vehicle class
// Derived class (Child)
class FuelCar extends Vehicle{
    private final String combustType;
    FuelCar(String name, String model, String combustType) {
        super(name, model);
        this.combustType = combustType;
    }
    public void getFuelCar() {
        getName();
        System.out.println(", combust type is " + combustType);
    }
}

// Hierarchical inheritance
// Alongside the FuelCar class, the ElectricCar class is also extending from Vehicle class
// Another Derived class (Child)
class ElectricCar extends Vehicle {
    private final String batteryPower;
    ElectricCar(String name, String model, String batteryPower) {
        super(name, model);
        this.batteryPower = batteryPower;
    }
    public void getElectricCar() {
        getName();
        System.out.print(", battery power is " + batteryPower);
    }
}


// Multi-level inheritance
// GasolineCar class is derived from the FuelCar class, which is further derived from the Vehicle class
// Derived class (Grandchild)

class GasolineCar extends FuelCar {
    private String combustType;
    private final String gasCapacity;
    GasolineCar(String name, String model, String combustType, String gasCapacity) {
        super(name, model, combustType);
        this.gasCapacity = gasCapacity;
    }
    public void getGasolineCar() {
        getFuelCar();
        System.out.println(", gas capacity is " + gasCapacity);
    }
}

// Java does not support Multiple inheritance via classes


