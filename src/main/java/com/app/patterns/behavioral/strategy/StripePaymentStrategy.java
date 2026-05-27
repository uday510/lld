package com.app.patterns.behavioral.strategy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StripePaymentStrategy implements PaymentStrategy {
    private final StripeClient stripeClient;

    private final Map<String, PaymentResponse> paymentsDB = new ConcurrentHashMap<>();

    public StripePaymentStrategy(StripeClient stripeClient) {
        this.stripeClient = stripeClient;
    }


    @Override
    public PaymentResponse pay(double amount, PaymentContext context) {
       return paymentsDB.computeIfAbsent(context.getIdempotencyKey(), k -> {
          var intent = stripeClient.createPaymentIntent(amount, context.getCurrency());
          return PaymentResponse.success(intent.getId());
       });
    }

}
