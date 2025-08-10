package com.app.patterns.structural.flyweight;

public class Bullet {

    private BulletType bulletType; // Intrinsic property shared by all bullets
    private int x, y; // Extrinsic property unique to each bullet
    private int velocity;

    public Bullet(String color, int x, int y, int velocity) {
        this.bulletType = BulletTypeFactory.getBulletType(color);
        this.x = x;
        this.y = y;
        this.velocity = velocity;
        System.out.println("Creating...  " + x + " " + y + " " + velocity);
    }

    public void display() {
        System.out.println("Bullet at (" + x + ", " + y + ") moving at velocity " + velocity);
    }
}
