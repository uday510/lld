package com.app.patterns.structural.decorator;

public class MilkDecorator extends CoffeeDecorator {

    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double cost() {
        return coffee.cost() + 10;
    }

    @Override
    public String description() {
        return coffee.description() + ", Milk";
    }

}
