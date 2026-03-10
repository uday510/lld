package com.app.patterns.structural.adapter.payment;

public class PaymentService {

    private final PaymentGateway paymentGateway;

    public PaymentService(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public void makePayment(double amount) {
        this.validaAmount(amount);
        this.paymentGateway.processPayment(amount);
    }

    private void validaAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid payment amount");
        }
    }
}
