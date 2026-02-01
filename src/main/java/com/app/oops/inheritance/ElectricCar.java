package com.app.oops.inheritance;

// Hierarchical inheritance
// Alongside the FuelCar class, the ElectricCar class is also extending from Vehicle class
// Another Derived class (Child)
public class ElectricCar extends Vehicle {
    private String batterPower;

    ElectricCar(String name, String model, String batterPower) {
        super(name, model);
        this.batterPower = batterPower;
    }

    public void getElectricCar() {
        getName();
        System.out.print(", batter power is " + batterPower);
    }

}
