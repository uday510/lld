package com.app.pblms.parking_lot.fare;

import com.app.pblms.parking_lot.Ticket;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

        if (fare.compareTo(BigDecimal.ONE) < 0) {
            fare = BigDecimal.ONE;
        }

        return fare.setScale(2, RoundingMode.HALF_UP);
    }

}
