package com.app.patterns.structural.decorator;

public class RegularCoffee implements Coffee {

    @Override
    public double cost() {
        return 50;
    }

    @Override
    public String description() {
        return "Simple Coffee";
    }
}
