package com.app.oops.abstraction;

// Concrete class implementing shape and Drawable
abstract class Circle extends Shape implements Drawable {
    private double radius;

    public Circle(String color, double radius) {
        super(color);
        this.radius = radius;
    }

    // Implementing abstract method from shape
    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    // Implementing method from Drawable interface
    @Override
    public void draw() {
        System.out.println("Drawing a circle.");
    }

}
