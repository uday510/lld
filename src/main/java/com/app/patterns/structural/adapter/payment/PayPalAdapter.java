package com.app.patterns.structural.adapter.payment;

public class PayPalAdapter implements PaymentProcessor {

    private PayPalService paypal;

    public PayPalAdapter(PayPalService paypal) {
        this.paypal = paypal;
    }

    @Override
    public void pay(double amount) {
        paypal.makePayment(amount);
    }

}
