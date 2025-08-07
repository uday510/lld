//package com.app.patterns.behavioral.command;
//
//// Encapsulate a request as an object, letting you parameterize clients, queue requests, and support undoable operations.
//
//interface Command {
//    void execute();
//}
//
//class Light {
//    public void on() {
//        System.out.println("Light is ON");
//    }
//    public void off() {
//        System.out.println("Light is OFF");
//    }
//}
//
//class Stereo {
//    public void on() {
//        System.out.println("Stereo is ON");
//    }
//    public void off() {
//        System.out.println("Stereo is OFF");
//    }
//    public void setCD() {
//        System.out.println("CD is set");
//    }
//    public void setVolume(int level) {
//        System.out.println("Volume is set to " + level);
//    }
//}
//
//class LightOnCommand implements Command {
//    Light light;
//
//    public LightOnCommand(Light light) {
//        this.light = light;
//    }
//
//    public void execute() {
//        light.on();
//    }
//}
//
//class StereoOnWithCDCommand implements Command {
//    Stereo stereo;
//
//    public StereoOnWithCDCommand(Stereo stereo) {
//        this.stereo = stereo;
//    }
//
//    public void execute() {
//        stereo.on();
//        stereo.setCD();
//        stereo.setVolume(11);
//    }
//}
//
//class SimpelRemoteControl {
//    Command slot;
//
//    public void setCommand(Command slot) {
//        this.slot = slot;
//    }
//
//    public void buttonWasPressed() {
//        slot.execute();
//    }
//}
//
//public class RemoteControlTest {
//
//    public static void main(String[] args) {
//        SimpelRemoteControl remote = new SimpelRemoteControl();
//
//        Light light = new Light();
//        Stereo stereo = new Stereo();
//
//        LightOnCommand lightOn = new LightOnCommand(light);
//        StereoOnWithCDCommand stereoOn = new StereoOnWithCDCommand(stereo);
//
//        remote.setCommand(lightOn);
//        remote.buttonWasPressed();
//
//        remote.setCommand(stereoOn);
//        remote.buttonWasPressed();
//    }
//
//}
