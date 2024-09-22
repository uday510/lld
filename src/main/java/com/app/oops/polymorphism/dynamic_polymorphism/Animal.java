package com.app.oops.polymorphism.dynamic_polymorphism;

public class Animal {
    public void printAnimal() {
        System.out.println("I am from the Animal class\n");
    }
    void printAnimalTwo() {
        System.out.println("I am from the Animal class\n");
    }
}

class Lion extends Animal {
    // method overriding
    public void printAnimal() {
        System.out.println("I am from the Lion class\n");
    }
}

class Tiger extends Animal {
    // method overriding
    public void printAnimal() {
        System.out.println("I am from the Tiger class\n");
    }
}
