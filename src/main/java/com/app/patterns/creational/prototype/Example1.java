package com.app.patterns.creational.prototype;

class Car {
    private String engine;
    private String wheels;
    private String seats;
    private boolean hasSunroof;

    public Car(String engine, String wheels, String seats, boolean hasSunroof) {
        this.engine = engine;
        this.wheels = wheels;
        this.seats = seats;
        this.hasSunroof = hasSunroof;
    }

    @Override
    public String toString() {
        return "Car [engine=" + engine + ", wheels=" + wheels + ", seats=" + seats + ", sunroof=" + hasSunroof + "]";
    }
}

class CarBuilder {
    private String engine;
    private String wheels;
    private String seats;
    private boolean hasSunroof;

    public CarBuilder setEngine(String engine) {
        this.engine = engine;
        return this;
    }

    public CarBuilder setWheels(String wheels) {
        this.wheels = wheels;
        return this;
    }

    public CarBuilder setSeats(String seats) {
        this.seats = seats;
        return this;
    }

    public CarBuilder setSunroof(boolean hasSunroof) {
        this.hasSunroof = hasSunroof;
        return this;
    }

    public Car build() {
        return new Car(engine, wheels, seats, hasSunroof);
    }
}

class HTMLBuilder {
    private StringBuilder html = new StringBuilder();

    public HTMLBuilder addHeader(String text) {
        html.append("<h1>").append(text).append("</h1>");
        return this;
    }

    public HTMLBuilder addParagraph(String text) {
        html.append("<p>").append(text).append("</p>");
        return this;
    }

    public String build() {
        return html.toString();
    }
}

public class Example1 {

    public static void main(String[] args) {

        CarBuilder carBuilder = new CarBuilder();
        Car car = carBuilder
                .setEngine("V8 Engine")
                .setWheels("Alloy Wheels")
                .setSeats("Leather Seats")
                .setSunroof(true)
                .build();

        System.out.println(car);

        System.out.println("\nHTML Example:");

        HTMLBuilder htmlBuilder = new HTMLBuilder();
        String html = htmlBuilder
                .addHeader("Title")
                .addParagraph("This is a paragraph.")
                .build();

        System.out.println(html);
    }
}
