package com.app.patterns.behavioral.strategy;

import java.util.Map;

public class PaymentContext {
    private final String userId;
    private final String currency;
    private final String idempotencyKey;
    private final Map<String, String> metadata;


    public PaymentContext(String userId, String currency, String idempotencyKey, Map<String, String> metadata) {
        this.userId = userId;
        this.currency = currency;
        this.idempotencyKey = idempotencyKey;
        this.metadata = metadata;
    }

    public String getUserId() {
        return userId;
    }

    public String getCurrency() {
        return currency;
    }

    public String getIdempotencyKey() {
        return idempotencyKey;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }
}
