package com.app.pblms.parking_lot.fare;

import com.app.pblms.parking_lot.Ticket;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Applies a multiplier during peak hours.
 */
public class PeakHourFareStrategy implements FareStrategy {

    private static final BigDecimal PEAK_MULTIPLIER = new BigDecimal("1.5");

    public BigDecimal calculateFare(Ticket ticket, BigDecimal amount) {
        LocalDateTime entryTime = ticket.getEntryTime();

        if (isPeakHour(entryTime)) {
            return amount.multiply(PEAK_MULTIPLIER);
        }

        return amount;
    }

    private boolean isPeakHour(LocalDateTime time) {
        int hr = time.getHour();
        return (hr >= 7 && hr <= 10) || (hr >= 16 && hr <= 19);
    }

}
