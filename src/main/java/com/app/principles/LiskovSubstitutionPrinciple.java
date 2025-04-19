package com.app.principles;


/*
Objects of a superclass should be replaceable with objects
 of its subclasses without breaking the application.
 */

interface Shape {
    int getArea();
}

class Rectangle implements Shape {
    private int width;
    private int height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getArea() {
        return width * height;
    }
}

class Square implements Shape {
    private int side;

    public Square(int side) {
        this.side = side;
    }

    public int getArea() {
        return side * side;
    }
}

public class LiskovSubstitutionPrinciple {

    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle(5, 5);
        System.out.println(rectangle.getArea());

        Square square = new Square(5);
        System.out.println(square.getArea());
    }
}
