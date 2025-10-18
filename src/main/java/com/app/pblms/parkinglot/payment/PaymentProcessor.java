package com.app.pblms.parkinglot.payment;

import java.math.BigDecimal;


public interface PaymentProcessor {

    Payment process(BigDecimal amount);
}
