package com.app.patterns.behavioral.strategy;

public interface StripeClient {
    PaymentIntent createPaymentIntent(double amount, String currency);
}
