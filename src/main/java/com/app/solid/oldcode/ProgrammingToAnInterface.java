package com.app.solid.oldcode;

interface Animal {
    void makeSound();
    void eat();
}

class Dog implements Animal {
    @Override
    public void makeSound() {
        System.out.println("Dog is barking...");
    }

    @Override
    public void eat() {
        System.out.println("Dog is eating.");
    }
}

class Lion implements Animal {
    @Override
    public void makeSound() {
        System.out.println("Lion is roaring...");
    }

    @Override
    public void eat() {
        System.out.println("Lion is eating.");
    }
}

public class ProgrammingToAnInterface {

    private final Animal animal;

    public ProgrammingToAnInterface(Animal animal) {
        this.animal = animal;
    }

    public void demonstrateBehavior() {
        animal.makeSound();
        animal.eat();
    }

    public static void main(String[] args) {
        ProgrammingToAnInterface dogBehavior = new ProgrammingToAnInterface(new Dog());
        dogBehavior.demonstrateBehavior();

        ProgrammingToAnInterface lionBehavior = new ProgrammingToAnInterface(new Lion());
        lionBehavior.demonstrateBehavior();
    }
}