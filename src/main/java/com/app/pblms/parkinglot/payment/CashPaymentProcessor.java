package com.app.pblms.parkinglot.payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Handles simple cash-based payments
 */
public class CashPaymentProcessor implements PaymentProcessor {

    @Override
    public Payment process(BigDecimal amount) {

        return new Payment(amount, PaymentMethod.CASH, LocalDateTime.now(), PaymentStatus.SUCCESS);
    }
}
