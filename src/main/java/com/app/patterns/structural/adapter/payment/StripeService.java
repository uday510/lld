package com.app.patterns.structural.adapter.payment;

public class StripeService {

    public void sendPayment(double value) {
        System.out.println("Paid via Stripe: " + value);
    }

}
