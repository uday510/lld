package com.app.pblms.basic.parking_lot.payment;

import java.math.BigDecimal;


public interface PaymentProcessor {

    Payment process(BigDecimal amount);
}
