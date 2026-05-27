package com.app.patterns.behavioral.state;

public interface WebhookState {
    void attempt(Webhook webhook);
    void markDelivered(Webhook webhook);
    WebhookStatus name();
}
