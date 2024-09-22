package com.app.oops.abstraction;

public class Circle {
    // define data attributes
    private final double radius;
    private final double pi;

    // define constructors
    public Circle() {
        this.radius = 0;
        this.pi = 3.14;
    }

    public Circle(double r) {
        radius = r;
        pi = 3.142;
    }

    // define methods
   public double area() {
        return pi * radius * radius;
   }

    public double perimeter() {
         return 2 * pi * radius;
    }

    public static void main(String[] args) {
        Circle circle = new Circle(5);
        System.out.printf("Area: %.2f %n", circle.area());
        System.out.printf("Perimeter: %.2f %n", circle.perimeter());
    }
}
