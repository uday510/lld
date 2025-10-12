package com.app.pblms.parkinglot.fare;

import java.math.BigDecimal;

public interface FareStrategy {
    BigDecimal calculateFare(Ticket ticket, BigDecimal baseFar);
}
