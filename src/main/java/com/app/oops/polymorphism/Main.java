package com.app.oops.polymorphism;

public class Main {

    static void main(String[] args) {
//        Animal animal = new Lion();
//        animal.printAnimal();;
//        animal.printAnimalTwo();
//
//        Area area = new Area();
//        System.out.print("Area of rectangle = " + area.calculateArea(3, 4));
//        System.out.print("\n");
//        System.out.print("Area of square = " + area.calculateArea(6));


        MathOperations math = new MathOperations();

        int s1 = math.add(5, 10);
        double s2 = math.add(3.5, 7.2);
        String s = math.add("Hello", ", World!");

        System.out.println("Sum of integers: " + s1);
        System.out.println("Sum of doubles: " + s1);
        System.out.println("Concatenated string: " + s);

    }
}
