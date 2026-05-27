package com.app.patterns.structural.decorator.old;

public class BasicPizza implements Pizza {

    @Override
    public String getDescription() {
        return "Basic Pizza";
    }

    @Override
    public double getCost() {
        return 4.00;
    }
}
