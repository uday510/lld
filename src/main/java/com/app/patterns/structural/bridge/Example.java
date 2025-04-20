package com.app.patterns.structural.bridge;

// Decouple abstraction from implementation so they can vary independently.

interface Device {
    void turnOn();
    void turnOff();
    void setVolume(int volume);
}

class TV implements Device {
    @Override
    public void turnOn() {
        System.out.println("TV is ON");
    }
    @Override
    public void turnOff() {
        System.out.println("TV is OFF");
    }

    @Override
    public void setVolume(int volume) {

    }
}

class Radio implements Device {
    @Override
    public void turnOn() {
        System.out.println("Radio is ON");
    }
    @Override
    public void turnOff() {
        System.out.println("Radio is OFF");
    }

    @Override
    public void setVolume(int volume) {

    }
}
abstract class RemoteControl {
    protected Device device;

    public RemoteControl(Device device) {
        this.device = device;
    }

    public abstract void togglePower();
    public abstract void volumeUp();
}

class BasicRemote extends RemoteControl {
    private boolean isOn = false;
    private int volume = 10;

    public BasicRemote(Device device) {
        super(device);
    }

    public void togglePower() {
        if (isOn) {
            device.turnOff();
        } else {
            device.turnOn();
        }
        isOn = !isOn;
    }

    public void volumeUp() {
        ++volume;
        device.setVolume(volume);
    }
}

public class Example {

    public static void main(String[] args) {
        Device tv = new TV();
        RemoteControl remote = new BasicRemote(tv);

        remote.togglePower();
        remote.volumeUp();
    }
}
