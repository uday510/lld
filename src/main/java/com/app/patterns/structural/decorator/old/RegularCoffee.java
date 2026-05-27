package com.app.patterns.structural.decorator.old;

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
