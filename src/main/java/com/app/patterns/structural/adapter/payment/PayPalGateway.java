package com.app.patterns.structural.adapter.payment;

public class PayPalGateway implements PaymentGateway {

    @Override
    public void processPayment(double amount) {
        System.out.println("Processing " + amount);
    }

}
