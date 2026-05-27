package com.app.patterns.behavioral.state;

public class Webhook {

    private WebhookState state;

    public Webhook() {
        this.state = new PendingState();
    }

    // Public API
    public void attempt()           { state.attempt(this); }
    public void markDelivered()     { state.markDelivered(this); }


    void setState(WebhookState newState) {
        System.out.println("    transition: " + state.name() + " -> " + newState.name());
        this.state = newState;
    }

    public WebhookStatus getStateName()    { return state.name(); }
}
