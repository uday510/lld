package com.app.patterns.creational.factory;

public class Bike implements Transport {

    @Override
    public void deliver() {
        System.out.println("Deliver by bike.");
    }

}
