package com.app.patterns.structural.flyweight;

public class BadBullet {

    private String color;
    private int x, y;
    private int velocity;


    public BadBullet(String color, int x, int y, int velocity) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.velocity = velocity;
        System.out.println("Creating BadBullet...  " + x + " " + y + " " + velocity);
    }

    public void display() {
        System.out.println("BadBullet at (" + x + ", " + y + ") moving at velocity " + velocity);
    }
}
