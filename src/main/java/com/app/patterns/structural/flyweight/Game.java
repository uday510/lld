package com.app.patterns.structural.flyweight;

public class Game {

    public static void main(String[] args) {

        // BadCode
        // 5 Blue Bullets
//        for (int i = 0; i < 5; ++i) {
//            BadBullet badBullet = new BadBullet("Blue", i * 10, i * 12, 5);
//        }
//
//        // 5 White Bullets
//        for (int i = 0; i < 5; ++i) {
//            BadBullet badBullet = new BadBullet("White", i * 10, i * 12, 5);
//        }

        // GoodCode

        // 5 Blue Bullets
        for (int i = 0; i < 5; ++i) {
            Bullet bullet = new Bullet("Blue", i * 10, i * 12, 5);
        }
//        // 5 White Bullets
        for (int i = 0; i < 5; ++i) {
            Bullet bullet = new Bullet("White", i * 10, i * 12, 5);
        }
    }
}
