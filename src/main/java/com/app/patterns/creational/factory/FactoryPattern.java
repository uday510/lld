package com.app.patterns.creational.factory;

abstract class Pizza {
    String name;

    void prepare() {
        System.out.println("Preparing " + name);
    }

    void bake() {
        System.out.println("Baking " + name);
    }

    void cut() {
        System.out.println("Cutting " + name);
    }

    void box() {
        System.out.println("Boxing " + name);
    }

    public String getName() {
        return name;
    }
}

class NYStyleCheesePizza extends Pizza {
    public NYStyleCheesePizza() {
        name = "NY Style Sauce and Cheese Pizza";
    }
}

class ChicagoStyleCheesePizza extends Pizza {
    public ChicagoStyleCheesePizza() {
        name = "Chicago Style Deep Dish Cheese Pizza";
    }

    @Override
    void cut() {
        System.out.println("Cutting the pizza into square slices");
    }
}

abstract class PizzaStore {

    // Factory Method
    abstract Pizza createPizza(String type);

    // Template Method
    public Pizza orderPizza(String type) {
        Pizza pizza = createPizza(type);

        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();

        return pizza;
    }
}

class NYPizzaStore extends PizzaStore {
    @Override
    Pizza createPizza(String type) {
        if (type.equals("cheese")) {
            return new NYStyleCheesePizza();
        } else {
            return null;
        }
    }
}

class ChicagoPizzaStore extends PizzaStore {
    Pizza createPizza(String type) {
        if (type.equals("cheese")) {
            return new ChicagoStyleCheesePizza();
        }
        return null;
    }
}

public class FactoryPattern {

    public static void main(String[] args) {
        PizzaStore nyStore = new NYPizzaStore();
        PizzaStore chicagoStore = new ChicagoPizzaStore();

        Pizza pizza1 = nyStore.orderPizza("cheese");
        System.out.println("Alan Turing ordered a " + pizza1.getName() + "\n");

        Pizza pizza2 = chicagoStore.orderPizza("cheese");
        System.out.println("Ada Lovelace ordered a " + pizza2.getName() + "\n");
    }
}
