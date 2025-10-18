package com.app.pblms.parkinglot.fare;

import com.app.pblms.parkinglot.Ticket;

import java.math.BigDecimal;

public interface FareStrategy {

    BigDecimal calculateFare(Ticket ticket, BigDecimal amount);

}
