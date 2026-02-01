package com.app.oops.abstraction;

// Abstract class
public abstract class Shape {
    protected String color;

    public Shape(String color) {
        this.color = color;
    }

    // Abstract method
    public abstract double area();

    // Concrete method
    public void displayColor() {
        System.out.println("This shape is " + color);
    }

}
