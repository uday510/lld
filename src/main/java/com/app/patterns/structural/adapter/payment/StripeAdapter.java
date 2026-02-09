package com.app.patterns.structural.adapter.payment;

public class StripeAdapter implements PaymentProcessor {

    private StripeService stripe;

    public StripeAdapter(StripeService stripe) {
        this.stripe = stripe;
    }

    @Override
    public void pay(double amount) {
        stripe.sendPayment(amount);
    }

}
