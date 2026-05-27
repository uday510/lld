package com.app.patterns.structural.decorator.old;

public class MushroomDecorator extends PizzaDecorator {

    public MushroomDecorator(Pizza pizza) {
        super(pizza);
    }

    public String getDescription() {
        return decoratedPizza.getDescription() + ", Mushroom";
    }

    public double getCost() {
        return decoratedPizza.getCost() + 3.50;
    }
}
