package com.app.patterns.structural.facade;

public class Main {

    static void main() {

        Coffee coffee = new RegularCoffee();

        coffee = new MilkDecorator(coffee);
        coffee = new SugarDecorator(coffee);

        System.out.println(coffee.description());
        System.out.println("Cost: " + coffee.cost());
    }

}
