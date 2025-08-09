package com.app.patterns.structural.proxy;

public class Client {

    public static void main(String[] args) {
        Image image1 = new ProxyImage("image1.jpg");
        Image image2 = new ProxyImage("image1.jpg");

        image1.display();
        image2.display();

    }
}
