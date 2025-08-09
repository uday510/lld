package com.app.patterns.structural.proxy;

public class RealImage implements Image {

    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadImageFromDisk();
    }

    public void loadImageFromDisk() {
        System.out.println("Image loaded from disk");
    }

    @Override
    public void display() {
        System.out.println(fileName + " displaying");
    }

}
