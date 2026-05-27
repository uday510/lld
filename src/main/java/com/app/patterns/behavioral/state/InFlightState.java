package com.app.patterns.behavioral.state;

public class InFlightState implements WebhookState {

    @Override
    public void attempt(Webhook webhook) {
        throw new IllegalArgumentException("Already in flight");
    }

    @Override
    public void markDelivered(Webhook webhook) {
        System.out.println("[in-flight] success - delivered");
        webhook.setState(new DeliveredState());
    }

    @Override
    public WebhookStatus name() { return WebhookStatus.IN_FLIGHT; }
}
