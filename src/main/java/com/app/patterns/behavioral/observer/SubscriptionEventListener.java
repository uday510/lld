package com.app.patterns.behavioral.observer;

public interface SubscriptionEventListener {
    void onSubscriptionUpgraded(SubscriptionUpgradedEvent event);
}
