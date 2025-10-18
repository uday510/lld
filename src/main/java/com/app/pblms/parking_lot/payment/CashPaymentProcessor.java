package com.app.pblms.parking_lot.payment;


import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CashPaymentProcessor implements PaymentProcessor {

    @Override
    public Payment process(BigDecimal amount) {
        return new Payment(amount, PaymentMethod.CASH, PaymentStatus.SUCCESS , LocalDateTime.now());
    }
}
