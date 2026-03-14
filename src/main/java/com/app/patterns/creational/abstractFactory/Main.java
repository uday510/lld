package com.app.patterns.creational.abstractFactory;

public class Main {

    static void main() {

//        UIFactory factory;
//
//        String os = System.getProperty("os.name");
//
//        if (os.contains("Windows")) {
//            factory = new WindowsFactory();
//        } else {
//            factory = new MacFactory();
//        }
//
//        Button b = factory.createButton();
//        Checkbox c = factory.createCheckbox();
//
//        b.paint();
//        c.paint();


        CloudService service = new CloudService(new AWSFactory());

        service.run();
    }
}
