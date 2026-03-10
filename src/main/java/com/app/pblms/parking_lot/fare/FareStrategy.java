package com.app.pblms.parking_lot.fare;

import com.app.pblms.parking_lot.ticket.Ticket;

import java.math.BigDecimal;

public interface FareStrategy {

    BigDecimal calculateFare(Ticket ticket, BigDecimal currentFare);

}
