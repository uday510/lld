package com.app.patterns.behavioral.observer;

public class WelcomeEmailListener implements SubscriptionEventListener {
    @Override
    public void onSubscriptionUpgraded(SubscriptionUpgradedEvent event) {
        System.out.println("[email] Welcome email sent to user " + event.userId()
        + " for plan " + event.newPlan());
    }
}
