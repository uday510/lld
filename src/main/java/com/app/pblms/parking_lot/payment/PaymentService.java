package com.app.pblms.parking_lot.payment;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.Map;

public class PaymentService {

    private final Map<PaymentMethod, PaymentProcessor> paymentProcessors;

    public PaymentService() {
        paymentProcessors = new EnumMap<>(PaymentMethod.class);
        paymentProcessors.put(PaymentMethod.CASH, new CashProcessor());
        paymentProcessors.put(PaymentMethod.CREDIT_CARD, new CreditCardProcessor());
    }

    public Payment pay(BigDecimal amount, PaymentMethod paymentMethod) {
        PaymentProcessor paymentProcessor = paymentProcessors.get(paymentMethod);
        if (paymentProcessor == null) {
            throw new IllegalArgumentException("Unsupported payment method: " + paymentMethod);
        }

        return paymentProcessor.pay(amount);
    }
}
