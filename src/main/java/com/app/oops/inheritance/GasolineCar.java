package com.app.oops.inheritance;

public class GasolineCar extends FuelCar {
    private String combustType;
    private String gasCapacity;
    GasolineCar(String name, String model, String combustType, String gasCapacity) {
        super(name, model, combustType);
        this.gasCapacity = gasCapacity;
    }
    public void getGasolineCar() {
        getName();
        System.out.print(", gas capacity is " + gasCapacity);
    }
}
