package com.app.pblms.basic.parking_lot.payment;

import java.math.BigDecimal;
import java.util.UUID;

public class CashPaymentProcessor implements PaymentProcessor {

    @Override
    public Payment process(BigDecimal amount) {

        return new Payment(
                UUID.randomUUID().toString(),
                PaymentMethod.CASH,
                amount,
                PaymentStatus.SUCCESS
        );

    }

}
