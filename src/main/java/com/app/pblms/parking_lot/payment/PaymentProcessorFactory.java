package com.app.pblms.parking_lot.payment;

import java.util.Map;
import java.util.EnumMap;

public class PaymentProcessorFactory {

    private final Map<PaymentMethod, PaymentProcessor> paymentProcessors;

    public PaymentProcessorFactory() {
        paymentProcessors = new EnumMap<>(PaymentMethod.class);
        paymentProcessors.put(PaymentMethod.CASH, new CashPaymentProcessor());
        paymentProcessors.put(PaymentMethod.CREDIT_CARD, new CreditCardPaymentProcessor());
    }

    public PaymentProcessor getProcessor(PaymentMethod paymentMethod) {
        PaymentProcessor paymentProcessor = paymentProcessors.get(paymentMethod);
        if (paymentProcessor == null) {
            throw new IllegalArgumentException("Unsopported payment method: " + paymentMethod);
        }

        return paymentProcessor;
    }

}
