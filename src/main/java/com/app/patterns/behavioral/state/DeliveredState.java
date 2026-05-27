package com.app.patterns.behavioral.state;

public class DeliveredState implements WebhookState {

    @Override
    public void attempt(Webhook webhook) {
        throw new IllegalStateException("Already delivered");
    }

    @Override
    public void markDelivered(Webhook webhook) {
        // idempotent no-op
    }

    @Override
    public WebhookStatus name() { return WebhookStatus.DELIVERED; }
}
