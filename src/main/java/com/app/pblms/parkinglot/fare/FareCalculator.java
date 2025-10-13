package com.app.pblms.parkinglot.fare;

import com.app.pblms.parkinglot.ticket.Ticket;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * FareCalculator applies multiple FareStrategy rules in sequence.
 */
public class FareCalculator {

    private final List<FareStrategy> fareStrategies;

    public FareCalculator(List<FareStrategy> fareStrategies) {
        this.fareStrategies = fareStrategies;
    }

    /**
     * Calculates the total fare by applying all strategies.
     * @param ticket ticket for which fare is being calculated
     * @return total fare
     */
    public BigDecimal calculateFare(Ticket ticket) {
        BigDecimal fare = BigDecimal.ZERO;
        for (FareStrategy strategy : fareStrategies) {
            fare = strategy.calculateFare(ticket, fare);
        }
        return fare;
    }
}
