package com.app.patterns.structural.facade;


class Amplifier {
    public void on() {
        System.out.println("Amplifier on");
    }
    public void off() {
        System.out.println("Amplifier off");
    }
}

class DVDPlayer {
    public void on() {
        System.out.println("DVD Player on");
    }
    public void off() {
        System.out.println("DVD Player off");
    }
    public void play(String movie) {
        System.out.println("Playing " + movie);
    }
}

class Projector {
    public void on() {
        System.out.println("Projector on");
    }
    public void off() {
        System.out.println("Projector off");
    }
}

class Screen {
    public void up() {
        System.out.println("Screen up");
    }
    public void down() {
        System.out.println("Screen down");
    }
}

class Lights {
    public void dim() {
        System.out.println("Lights dim");
    }
}

class HomeTheaterFacade {
    private Amplifier amplifier;
    private DVDPlayer dvdPlayer;
    private Projector projector;
    private Screen screen;
    private Lights lights;

    public HomeTheaterFacade(Amplifier amplifier, DVDPlayer dvdPlayer, Projector projector, Screen screen, Lights lights) {
        this.amplifier = amplifier;
        this.dvdPlayer = dvdPlayer;
        this.projector = projector;
        this.screen = screen;
        this.lights = lights;
    }

    public void watchMovie(String movie) {
        System.out.println("Get ready to watch a movie...");
        lights.dim();
        screen.down();
        projector.on();
        amplifier.on();
        dvdPlayer.on();
        dvdPlayer.play(movie);
    }

    public void endMovie() {
        System.out.println("Shutting movie theater down...");
        lights.dim();
        screen.up();
        projector.off();
        amplifier.off();
        dvdPlayer.off();
    }
}

public class HomeTheaterTestDrive {

    public static void main(String[] args) {
        Amplifier amplifier = new Amplifier();
        DVDPlayer dvdPlayer = new DVDPlayer();
        Projector projector = new Projector();
        Screen screen = new Screen();
        Lights lights = new Lights();

        HomeTheaterFacade homeTheater = new HomeTheaterFacade(amplifier, dvdPlayer, projector, screen, lights);

        homeTheater.watchMovie("The Dark Knight");

        System.out.println("--------------------");

        homeTheater.endMovie();

    }
}
