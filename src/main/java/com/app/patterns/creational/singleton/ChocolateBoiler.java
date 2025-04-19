package com.app.patterns.creational.singleton;

public class ChocolateBoiler {
    private boolean empty;
    private boolean boiled;

    private ChocolateBoiler() {
        empty = true;
        boiled = false;
    }

    private static final class InstanceHolder {
        static final ChocolateBoiler instance = new ChocolateBoiler();
    }

    public static ChocolateBoiler getInstance() {
        return InstanceHolder.instance;
    }

    public void boil() {
        if (!isEmpty() && !isBoiled()) {
            boiled = true;
            System.out.println("Boiling chocolate...");
        }
    }

    public void drain() {
        if (!isEmpty() && isBoiled()) {
            empty = false;
            System.out.println("Draining chocolate...");
        }
    }

    public boolean isEmpty() {
        return empty;
    }

    public boolean isBoiled() {
        return boiled;
    }


    public static void main(String[] args) {
        ChocolateBoiler boiler = ChocolateBoiler.getInstance();
        System.out.println("Is boiler empty? " + boiler.isEmpty());
        System.out.println("Is boiler boiled? " + boiler.isBoiled());

        boiler.boil();
        System.out.println("Is boiler boiled? " + boiler.isBoiled());

        boiler.drain();
        System.out.println("Is boiler empty? " + boiler.isEmpty());
    }
}
