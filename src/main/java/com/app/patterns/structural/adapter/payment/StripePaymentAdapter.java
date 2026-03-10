package com.app.patterns.structural.adapter.payment;

public class StripePaymentAdapter implements PaymentGateway {

    private final StripeClient stripeClient;

    public StripePaymentAdapter(StripeClient stripeClient) {
        this.stripeClient = stripeClient;
    }

    @Override
    public void processPayment(double amount) {

        int cents = this.convertToCents(amount);

        this.stripeClient.charge(cents);
    }

    private int convertToCents(double amount) {
        return (int) Math.round(amount * 100);
    }
}
