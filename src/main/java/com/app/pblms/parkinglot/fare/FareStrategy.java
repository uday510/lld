package com.app.pblms.parkinglot.fare;

import com.app.pblms.parkinglot.ticket.Ticket;

import java.math.BigDecimal;

/**
 * Strategy interface for calculating parking fares
 * Implementations can apply different pricing rules.
 */
public interface FareStrategy {

    /**
     * Calculates the fare based on the given ticket and current fare.
     * @param ticket ticket details
     * @param currentFare current fare before applying this strategy
     * @return updated fare after applying this strategy
     */
    BigDecimal calculateFare(Ticket ticket, BigDecimal currentFare);
}
