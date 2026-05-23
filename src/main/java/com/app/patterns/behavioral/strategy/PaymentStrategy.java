package com.app.patterns.behavioral.strategy;

public interface PaymentStrategy {
    PaymentResponse pay(double amount, PaymentContext context);
}
