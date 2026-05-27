package com.app.patterns.behavioral.state.old;

public class Walking implements TransportationMode {

    @Override
    public int calculateETA() {
        return 100;
    }

    @Override
    public String getDirection() {
        return "Walk Slowly";
    }

}
