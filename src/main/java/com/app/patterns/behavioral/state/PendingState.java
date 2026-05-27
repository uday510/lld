package com.app.patterns.behavioral.state;

public class PendingState implements WebhookState {

    @Override
    public void attempt(Webhook webhook) {
        System.out.println("[pending] starting attempt");
        webhook.setState(new InFlightState());
    }

    @Override
    public void markDelivered(Webhook webhook) {
        throw new IllegalStateException("Cannot mark delivered");
    }

    @Override
    public WebhookStatus name() { return WebhookStatus.PENDING; }
}
