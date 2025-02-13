package com.app.oops.polymorphism;

public class Main {

    public static void main(String[] args) {
        Animal animal;
        Lion lion = new Lion();
        animal = lion;

        animal.printAnimal();;
        animal.printAnimalTwo();

        Area area = new Area();
        System.out.print("Area of rectangle = " + area.calculateArea(3, 4));
        System.out.print("\n");
        System.out.print("Area of square = " + area.calculateArea(6));
    }
}
