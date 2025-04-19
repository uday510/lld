package com.app.patterns.creational.abstractFactory;

abstract class Pizza {
    String name;
    Dough dough;
    Sauce sauce;
    Cheese cheese;

    abstract void prepare();

    void bake() {
        System.out.println("Bake for 25 minutes at 350");
    }

    void cut() {
        System.out.println("Cutting the pizza into diagonal slices");
    }

    void box() {
        System.out.println("Place pizza in official PizzaStore box");
    }

    void setName(String name) {
        this.name = name;
    }
    String getName() {
        return name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("---- " + name + " ----\n");
        if (dough != null) sb.append(dough + "\n");
        if (sauce != null) sb.append(sauce + "\n");
        if (cheese != null) sb.append(cheese + "\n");
        return sb.toString();
    }
}

abstract class PizzaStore {
    public Pizza orderPizza(String type) {
        Pizza pizza = createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }

    abstract Pizza createPizza(String type);
}

interface Dough {
    String toString();
}

interface Sauce {
     String toString();
}

interface Cheese {
    String toString();
}

class ThinCrustDough implements Dough {
    @Override
    public String toString() {
        return "Thin Crust Dough";
    }
}

class MarinaraSauce implements Sauce {
    @Override
    public String toString() {
        return "Marinara Sauce";
    }
}

class ReggianoCheese implements Cheese {
    @Override
    public String toString() {
        return "Reggiano Cheese";
    }
}

interface PizzaIngredientFactory {
    Dough createDough();
    Sauce createSauce();
    Cheese createCheese();
}

class NYPizzaIngredientFactory implements PizzaIngredientFactory {
    @Override
    public Dough createDough() {
        return new ThinCrustDough();
    }

    @Override
    public Sauce createSauce() {
        return new MarinaraSauce();
    }

    @Override
    public Cheese createCheese() {
        return new ReggianoCheese();
    }
}

class CheesePizza extends Pizza {
    PizzaIngredientFactory ingredientFactory;

    public CheesePizza(PizzaIngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    void prepare() {
        System.out.println("Preparing " + name);
        dough = ingredientFactory.createDough();
        sauce = ingredientFactory.createSauce();
        cheese = ingredientFactory.createCheese();
    }
}

class NyPizzaStore extends PizzaStore {
    protected Pizza createPizza(String item) {
        Pizza pizza = null;
        PizzaIngredientFactory factory = new NYPizzaIngredientFactory();

        if (item.equals("cheese")) {
            pizza = new CheesePizza(factory);
            pizza.setName("New York Style Cheese Pizza");
        }

        return pizza;
    }
}

public class AbstractFactoryExample {

    public static void main(String[] args) {
        PizzaStore nyStore = new NyPizzaStore();
        Pizza pizza = nyStore.orderPizza("cheese");
        System.out.println("Ethan ordered a " + pizza.getName() + "\n");
        System.out.println(pizza);
    }
}
