package com.app.patterns.behavioral.state;

public class WithoutStatePattern {

    public static void main(String[] args) {

        BadCode directionService = new BadCode();
        directionService.setTransportationMode(Mode.CYCLING);

        System.out.println(directionService.getDirection());
        System.out.print(directionService.getETA());
    }

}
