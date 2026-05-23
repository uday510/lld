package com.app.patterns.behavioral.observer;

public class BrokenSlackListener implements SubscriptionEventListener {
    @Override
    public void onSubscriptionUpgraded(SubscriptionUpgradedEvent event) {
        throw new RuntimeException("Slack API is down");
    }
}
