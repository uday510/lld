package com.app.patterns.behavioral.state.old;

public class Main {

    public static void main(String[] args) {

        DirectionService directionService = new DirectionService(new Cycling());

        System.out.println(directionService.getDirection());;
        System.out.println(directionService.getETA());

        System.out.println("------------");

        directionService.setTransportationMode(new Train());
        System.out.println(directionService.getDirection());;
        System.out.println(directionService.getETA());

    }
}
