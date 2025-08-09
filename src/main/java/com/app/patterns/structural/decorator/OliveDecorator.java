package com.app.patterns.structural.decorator;

public class OliveDecorator extends PizzaDecorator {

    public OliveDecorator(Pizza pizza) {
        super(pizza);
    }

    public String getDescription() {
        return decoratedPizza.getDescription() + ", Olive";
    }

    public double getCost() {
        return decoratedPizza.getCost() + 2.50;
    }
}
