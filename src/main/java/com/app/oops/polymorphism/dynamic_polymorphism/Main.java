package com.app.oops.polymorphism.dynamic_polymorphism;

public class Main {
    public static void main(String[] args) {
        Animal animal = new Animal();
        animal.printAnimal();
        animal.printAnimalTwo();
        System.out.println("--------------------------------------------------");
        Lion lion = new Lion();
        lion.printAnimal();
        lion.printAnimalTwo();
        System.out.println("--------------------------------------------------");
        Tiger tiger = new Tiger();
        tiger.printAnimal();
        tiger.printAnimalTwo();
    }
}
