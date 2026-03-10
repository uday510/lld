package com.app.patterns.structural.adapter.payment;

public class StripeClient {

    public void charge(int amountInCents) {
        System.out.println("Charging " + amountInCents + " cents...");
    }

}
