package com.app.patterns.behavioral.strategy;

public class PaymentIntent {
    private final String id;
    private final String status;

    public PaymentIntent(String id, String status) {
        this.id = id;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }
}
