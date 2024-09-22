package com.app.oops.polymorphism.static_polymorphism;

public class Main {
    public static void main(String[] args) {
        Sum sum = new Sum();
        System.out.print(sum.addition(14, 35));
        System.out.print("\n");
        System.out.print(sum.addition(31, 34, 43));
    }
}
