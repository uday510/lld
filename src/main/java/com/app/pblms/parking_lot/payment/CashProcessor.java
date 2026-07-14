package com.app.pblms.parking_lot.payment;

import java.math.BigDecimal;
import java.util.UUID;

public class CashProcessor implements PaymentProcessor {

    @Override
    public Payment pay(BigDecimal amount) {

        return new Payment(
                UUID.randomUUID().toString(),
                PaymentMethod.CASH,
                amount,
                PaymentStatus.SUCCESS
        );

    }

}
