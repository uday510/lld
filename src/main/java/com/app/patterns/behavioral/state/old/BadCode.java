package com.app.patterns.behavioral.state.old;

enum Mode {
    WALKING, CYCLING, TRAIN
}


public class BadCode {

    private Mode mode;

    public void setTransportationMode(Mode mode) {
        this.mode = mode;
    }

    public int getETA() {
        switch (mode) {
            case WALKING:
                return 100;
            case CYCLING:
                return 50;
            case TRAIN:
                return 20;
            default:
                return -1;
        }
    }

    public String getDirection() {
        switch (mode) {
            case WALKING:
                return "Walk Slowly";
            case CYCLING:
                return "Cycle Slowly";
            case TRAIN:
                return "Ride Slowly";
            default:
                return "Don't Travel";
        }
    }

}
