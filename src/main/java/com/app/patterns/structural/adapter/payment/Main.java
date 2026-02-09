package com.app.patterns.structural.adapter.payment;

public class Main {

    static void main() {

        PaymentProcessor paypal =
                new PayPalAdapter(new PayPalService());

        PaymentProcessor stripe =
                new StripeAdapter(new StripeService());

        paypal.pay(1000);
        stripe.pay(500);
    }
}
