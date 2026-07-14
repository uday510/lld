package com.app.pblms.parking_lot.payment;

import java.math.BigDecimal;


public interface PaymentProcessor {

    Payment pay(BigDecimal amount);

}
