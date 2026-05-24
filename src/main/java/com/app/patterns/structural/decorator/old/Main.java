package com.app.patterns.structural.decorator.old;

public class Main {

    public static void main(String[] args) {
       // Basic Pizza
        Pizza pizza = new BasicPizza();

        pizza = new CheeseDecorator(pizza);
        pizza = new OliveDecorator(pizza);
        pizza = new MushroomDecorator(pizza);

        System.out.println(pizza.getDescription());
        System.out.println(pizza.getCost());

        System.out.println("\n------------");

        Coffee coffee = new RegularCoffee();

        coffee = new MilkDecorator(coffee);
        coffee = new SugarDecorator(coffee);

        System.out.println(coffee.description());
        System.out.println("Cost: " + coffee.cost());
    }
}
