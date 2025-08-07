package com.app.patterns.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

// Observer interface
interface Observer {
    void update(float temperature);
}

// Subject interface
interface Subject {
    void attach(Observer observer);
    void detach(Observer observer);
    void notifyAllObserver();
}

class MobileDevice implements Observer {

    @Override
    public void update(float temperature) {
        System.out.println("Mobile Device " + temperature);
    }

}

class DisplayDevice implements Observer {

    @Override
    public void update(float temperature) {
        System.out.println("Display Device " + temperature);
    }

}

public class WeatherStation implements Subject {

    private float temperature;
    private final List<Observer> observers = new ArrayList<>();

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
        notifyAllObserver();
    }

    @Override
    public void notifyAllObserver() {
        for (Observer observer : observers) {
            observer.update(temperature);
        }
    }

    public static void main(String[] args) {
        WeatherStation weatherStation = new WeatherStation();
        Observer mobileDevice = new MobileDevice();
        Observer displayDevice = new DisplayDevice();

        weatherStation.attach(mobileDevice);
        weatherStation.attach(displayDevice);

        weatherStation.setTemperature(100);
    }
}
