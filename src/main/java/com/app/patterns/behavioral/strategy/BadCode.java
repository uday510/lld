package com.app.patterns.behavioral.strategy;

public class BadCode {

    public void processPayment(String type, double amount) {

        if (type.equals("STRIPE")) {
            System.out.println("Processing via Stripe.." + amount);
        } else if (type.equals("GOOGLE_PLAY")) {
            System.out.println("Processing via Google Play.." + amount);
        } else if (type.equals("APPLE_STORE")) {
            System.out.println("Processing via Apple Store.." + amount);
        }

        // ....
    }

}


/**
 *
 *
 * Open/Closed violation - adding a new gateway means modifying the class
 * Single Responsibility violation - this class knows the details of every gateway
 * Hard to test - you can't mock stripe
 * Hard to extend -
 */