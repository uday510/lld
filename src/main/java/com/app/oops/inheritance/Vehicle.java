package com.app.oops.inheritance;

// Base class (Parent)
public class Vehicle {
    private String name;
    private String model;
    Vehicle(String name, String model) {
        this.name = name;
        this.model = model;
    }
    public void getName() {
        System.out.print("The car is a " + name + " " + model);
    }
}
