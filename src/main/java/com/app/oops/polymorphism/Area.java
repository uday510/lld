package com.app.oops.polymorphism;

public class Area {
    public double calculateArea(double length, double breath) {
        return length * breath;
    }

    public double calculateArea(double side) {
        return side * side;
    }
}
