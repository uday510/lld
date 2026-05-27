package com.app.patterns.behavioral.observer;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Flow;

public class SubscriptionEventPublisher {
    private final List<SubscriptionEventListener> listeners = new CopyOnWriteArrayList<>();

    public void subscribe(SubscriptionEventListener listener) {
        listeners.add(listener);
    }

    public void unsubscribe(SubscriptionEventListener listener) {
        listeners.remove(listener);
    }

    public void publish(SubscriptionUpgradedEvent event) {
        for (SubscriptionEventListener listener : listeners) {
            try {
                listener.onSubscriptionUpgraded(event);
            } catch (Exception e) {
                System.err.println("Listener " + listener.getClass().getSimpleName()
                + " failed: " + e.getMessage());
            }
        }
    }
}
