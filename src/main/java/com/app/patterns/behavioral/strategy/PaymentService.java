package com.app.patterns.behavioral.strategy;

public class PaymentService {

    private PaymentStrategy strategy;

    public PaymentService(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public PaymentResponse processPayment(double amount, PaymentContext context) {
        return strategy.pay(amount, context);
    }
}
