package com.app.patterns.structural.facade;

public class PaymentService {

    public String processPayment(String paymentId) {
        return "Processing Payment [ paymentId: " + paymentId + " ]";
    }
}
