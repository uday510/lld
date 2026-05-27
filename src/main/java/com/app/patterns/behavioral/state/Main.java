package com.app.patterns.behavioral.state;

public class Main {

    static void main() {

        Webhook hook = new Webhook();
        System.out.println("Initial state: " + hook.getStateName());

        hook.attempt();
        hook.markDelivered();;
        System.out.println("Final state: " + hook.getStateName());


        System.out.println("\n--- trying illegal transition");
        try {
            hook.attempt();
        } catch (IllegalStateException e) {
            System.out.println("Caught expected: " + e.getMessage());
        }

    }
}
