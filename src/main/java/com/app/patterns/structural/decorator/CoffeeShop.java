package com.app.patterns.structural.decorator;


interface Coffee {
    double cost();
}

class SimpleCoffee implements Coffee {
    public double cost() {
        return 5.0;
    }
}

abstract class CoffeeDecorator implements Coffee {
    protected Coffee coffee;

    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    public double cost() {
        return coffee.cost();
    }
}

class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double cost() {
        return super.cost() + 1.5;
    }
}

class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double cost() {
        return super.cost() + 1.5;
    }
}

public class CoffeeShop {

    public static void main(String[] args) {
        Coffee simpleCoffee = new SimpleCoffee();
        System.out.println("Cost of simple coffee: $" + simpleCoffee.cost());

        Coffee milkCoffee = new MilkDecorator(simpleCoffee);
        System.out.println("Cost of coffee with milk: $" + milkCoffee.cost());

        Coffee sugarCoffee = new SugarDecorator(simpleCoffee);
        System.out.println("Cost of coffee with sugar: $" + sugarCoffee.cost());
    }
}
