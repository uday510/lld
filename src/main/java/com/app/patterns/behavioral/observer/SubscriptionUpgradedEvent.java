package com.app.patterns.behavioral.observer;

import java.time.Instant;

public record SubscriptionUpgradedEvent(
   String userId,
   String oldPlan,
   String newPlan,
   Instant occurredAt
) {}