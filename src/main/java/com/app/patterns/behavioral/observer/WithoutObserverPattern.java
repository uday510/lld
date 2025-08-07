//package com.app.patterns.behavioral.observer;
//
//class DisplayDevice {
//    public void showTemperature(float temperature) {
//        System.out.println("Current Temperature : " + temperature);
//    }
//}
//
//class WeatherStation {
//    private float temperature;
//    private DisplayDevice displayDevice;
//
//    public WeatherStation(DisplayDevice displayDevice) {
//        this.displayDevice = displayDevice;
//    }
//
//    public float getTemperature() {
//        return temperature;
//    }
//
//    public void setTemperature(float temperature) {
//        this.temperature = temperature;
//        notifyDevices();
//    }
//
//    public void notifyDevices() {
//        displayDevice.showTemperature(temperature);
//    }
//}
//
//public class WithoutObserverPattern {
//
//    public static void main(String[] args) {
//
//        DisplayDevice displayDevice = new DisplayDevice();
//        WeatherStation weatherStation = new WeatherStation(displayDevice);
//
//        weatherStation.setTemperature(26);
//        weatherStation.setTemperature(30);
//    }
//}
