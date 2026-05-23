package com.app.patterns.behavioral.observer;

import java.time.Instant;

public class Main {

    static void main() {

        SubscriptionEventPublisher publisher = new SubscriptionEventPublisher();

        publisher.subscribe(new WelcomeEmailListener());
        publisher.subscribe(new BrokenSlackListener());
        publisher.subscribe(new AnalyticsListener());

        SubscriptionUpgradedEvent event = new SubscriptionUpgradedEvent(
          "user-12354",
          "FREE",
          "PRO",
                Instant.now()
        );

        publisher.publish(event);
    }
}
