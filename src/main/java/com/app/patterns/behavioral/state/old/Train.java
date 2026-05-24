package com.app.patterns.behavioral.state.old;

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
