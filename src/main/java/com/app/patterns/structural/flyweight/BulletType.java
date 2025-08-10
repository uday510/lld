package com.app.patterns.structural.flyweight;

// Flyweight class
public class BulletType {

    private final String color; // Intrinsic Property

    public BulletType(String color) {
        this.color = color;
        System.out.println("Creating bullet with color " + color);
    }

    public String getColor() {
        return color;
    }

}
