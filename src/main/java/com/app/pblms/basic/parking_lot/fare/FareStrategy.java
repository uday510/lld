package com.app.pblms.basic.parking_lot.fare;

import com.app.pblms.basic.parking_lot.ticket.Ticket;

import java.math.BigDecimal;

public interface FareStrategy {

    BigDecimal calculateFare(Ticket ticket, BigDecimal currentFare);

}
