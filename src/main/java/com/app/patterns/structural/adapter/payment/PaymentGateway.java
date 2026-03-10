package com.app.patterns.structural.adapter.payment;

public interface PaymentGateway {
    void processPayment(double amount);
}
