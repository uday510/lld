package com.app.patterns.creational.factory;

abstract class Pizza1 {
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

class NYStyleCheesePizza1ForFactoryPattern extends Pizza1 {
    public NYStyleCheesePizza1ForFactoryPattern() {
        name = "NY Style Sauce and Cheese Pizza";
    }
}

class ChicagoStyleCheesePizza1ForFactoryPattern extends Pizza1 {
    public ChicagoStyleCheesePizza1ForFactoryPattern() {
        name = "Chicago Style Deep Dish Cheese Pizza";
    }

    @Override
    void cut() {
        System.out.println("Cutting the pizza into square slices");
    }
}

abstract class PizzaStore1 {

    // Factory Method
    abstract Pizza1 createPizza(String type);

    // Template Method
    public Pizza1 orderPizza(String type) {
        Pizza1 pizza1ForFactoryPattern = createPizza(type);

        pizza1ForFactoryPattern.prepare();
        pizza1ForFactoryPattern.bake();
        pizza1ForFactoryPattern.cut();
        pizza1ForFactoryPattern.box();

        return pizza1ForFactoryPattern;
    }
}

class NYPizzaStore1 extends PizzaStore1 {
    @Override
    Pizza1 createPizza(String type) {
        if (type.equals("cheese")) {
            return new NYStyleCheesePizza1ForFactoryPattern();
        } else {
            return null;
        }
    }
}

class ChicagoPizzaStore1 extends PizzaStore1 {
    Pizza1 createPizza(String type) {
        if (type.equals("cheese")) {
            return new ChicagoStyleCheesePizza1ForFactoryPattern();
        }
        return null;
    }
}

public class FactoryPattern {

    public static void main(String[] args) {
        PizzaStore1 nyStore = new NYPizzaStore1();
        PizzaStore1 chicagoStore = new ChicagoPizzaStore1();

        Pizza1 pizza1ForFactoryPattern1 = nyStore.orderPizza("cheese");
        System.out.println("Alan Turing ordered a " + pizza1ForFactoryPattern1.getName() + "\n");

        System.out.println("-----------------------------");

        Pizza1 pizza1ForFactoryPattern2 = chicagoStore.orderPizza("cheese");
        System.out.println("Ada Lovelace ordered a " + pizza1ForFactoryPattern2.getName() + "\n");
    }
}
