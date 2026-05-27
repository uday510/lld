package com.app.patterns.behavioral.observer;

public class AnalyticsListener implements SubscriptionEventListener {
    @Override
    public void onSubscriptionUpgraded(SubscriptionUpgradedEvent event) {
        System.out.println("[analytics] Recorded upgrade: " + event.userId()
                + " from " + event.oldPlan() + " to " + event.newPlan());
    }
}
