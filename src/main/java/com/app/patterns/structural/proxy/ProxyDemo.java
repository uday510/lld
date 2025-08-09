//package com.app.patterns.structural.proxy;
//
//interface Image {
//    void display();
//}
//
//class RealImage implements Image {
//    private String fileName;
//
//    public RealImage(String fileName) {
//        this.fileName = fileName;
//        loadFromDisk();
//    }
//
//    private void loadFromDisk() {
//        System.out.println("Loading " + fileName + " from disk...");
//    }
//
//    public void display() {
//        System.out.println("Displaying " + fileName);
//    }
//}
//
//class ImageProxy implements Image {
//    private String fileName;
//    private RealImage realImage;
//
//    public ImageProxy(String fileName) {
//        this.fileName = fileName;
//    }
//
//    public void display() {
//        if (realImage == null) {
//            realImage = new RealImage(fileName); // Lazy loading
//        }
//        realImage.display();
//    }
//}
//
//public class ProxyDemo {
//
//    public static void main(String[] args) {
//        Image image = new ImageProxy("cat.png");
//
//        System.out.println("Image created.");
//        System.out.println("Now displaying the image...");
//        image.display();
//
//        System.out.println("Displaying the image again...");
//        image.display();
//    }
//}
