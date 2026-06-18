package com.app.pblms.parking_lot.payment;

public class PaymentProcessorFactory {

    private final Map<PaymentMethod, PaymentProcessor> paymentProcessors;

    public PaymentProcessorFactory() {
        paymentProcessors = new EnumMap<>(PaymentMethod.class);
        processors.put(PaymentMethod.CASH, new CashPaymentProcessor());
        processors.put(PaymentMethod.CREDIT_CARD, new CreditCardPaymentProcessor();
    }

    public PaymentProcessor getProcessor(PaymentMethod paymentMethod) {
        PaymentProcessor paymentProcessor = paymentProcessors.get(paymentMethod);
        if (paymentProcessor == null) {
            throw new IllegalArgumentException("Unsopported payment method: " + paymentMethod);
        }

        return paymentProcessor;
    }

}
