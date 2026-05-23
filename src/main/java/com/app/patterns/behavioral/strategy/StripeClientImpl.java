package com.app.patterns.behavioral.strategy;


import com.github.f4b6a3.ulid.Ulid;


public class StripeClientImpl implements StripeClient {

    @Override
    public PaymentIntent createPaymentIntent(double amount, String currency) {

        return new PaymentIntent(Ulid.fast().toString(), "SUCCESS");

    }

}
