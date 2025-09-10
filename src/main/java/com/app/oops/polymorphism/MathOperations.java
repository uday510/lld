package com.app.oops.polymorphism;

// Compile-Time polymorphism via method overloading
public class MathOperations {

    public int add(int a, int b) {
        return a + b;
    }

    public double add(double a, double b) {
        return a + b;
    }

    public String add(String s1, String s2) {
        return s1 + s2;
    }
}