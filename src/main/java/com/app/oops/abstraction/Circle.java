package com.app.oops.abstraction;

public class Circle extends Shape implements Drawable {
    private final double radius;

    public Circle(String color, double radius) {
        super(color);
        this.radius = radius;
    }

    // Implementing abstract method from Shape
    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    // Implementing method form Drawable interface
    @Override
    public void draw() {
        System.out.println("Drawing a circle.");
    }
}

/*

  The implementation demonstrates abstraction through:

  The Shape abstract class defines an abstract area() method that subclasses must implement and a concrete displayColor()
   method, which provides a default behavior.

  The Drawable interface, which declares a draw() method that implementing classes must define.

  The Circle class extends Shape and implements Drawable, providing specific implementations for
  area() (calculating the circle’s area) and draw() (describing the drawing action).

*/