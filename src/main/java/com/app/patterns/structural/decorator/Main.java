package com.app.patterns.structural.decorator;

public class Main {

    public static void main(String[] args) {
       // Basic Pizza
        Pizza pizza = new BasicPizza();

        pizza = new CheeseDecorator(pizza);
        pizza = new OliveDecorator(pizza);
        pizza = new MushroomDecorator(pizza);

        System.out.println(pizza.getDescription());
        System.out.println(pizza.getCost());
    }
}
