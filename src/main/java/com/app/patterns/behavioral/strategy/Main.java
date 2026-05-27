package com.app.patterns.behavioral.strategy;

import com.github.f4b6a3.ulid.Ulid;

import java.util.Collections;
import java.util.UUID;

public class Main {

    static void main(String[] args) {

        PaymentService service = new PaymentService(
                new StripePaymentStrategy(new StripeClientImpl())
        );

        String idempotencyKey = "ORDER-" + Ulid.fast();

        PaymentContext context = new PaymentContext(
                "user-12354",
                "INR",
                idempotencyKey,
                Collections.emptyMap()
        );

        PaymentResponse response = service.processPayment(100.0, context);
        System.out.println(response);

        PaymentResponse retryResponse = service.processPayment(100.0, context);
        System.out.println(retryResponse);

    }
}