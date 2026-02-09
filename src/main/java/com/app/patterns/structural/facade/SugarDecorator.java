package com.app.patterns.structural.facade;

public class SugarDecorator extends CoffeeDecorator {

    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double cost() {
        return coffee.cost() + 5;
    }

    @Override
    public String description() {
        return coffee.description() + ", sugar";
    }

}
