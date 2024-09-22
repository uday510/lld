package com.app.oops.inheritance;

public class Main {
    public static void main(String[] args) {
        System.out.println("Single inheritance:");
        FuelCar Fuel = new FuelCar("Honda", "Accord", "Petrol");
        Fuel.getFuelCar();
        System.out.println("\n");

        System.out.println("Hierarchical inheritance:");
        ElectricCar Electric = new ElectricCar("Tesla", "ModelX", "200MWH");
        Electric.getElectricCar();
        System.out.println("\n");

        System.out.println("Multi-Level inheritance:");
        GasolineCar Gasoline = new GasolineCar("Toyota", "Corolla", "Gasoline", "30 liters");
        Gasoline.getGasolineCar();


        System.out.println("\n");
        System.out.println("Java does not support Multiple Inheritance through classes");
    }
}
