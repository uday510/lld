package com.app.patterns.behavioral.state;

public class Train implements TransportationMode {

    @Override
    public int calculateETA() {
        return 500;
    }

    @Override
    public String getDirection() {
        return "Ride Train Slowly";
    }

}
