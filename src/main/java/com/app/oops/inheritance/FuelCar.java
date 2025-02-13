package com.app.oops.inheritance;

// Single inheritance
// FuelCar class extending from Vehicle class
// Derived class (Child)
public class FuelCar extends Vehicle {
    private String combustType;
    FuelCar(String name, String model, String combustType) {
        super(name, model);
        this.combustType = combustType;
    }
    public void getFuelCar() {
        getName();
        System.out.print(", combust type is " + combustType);
    }
}
