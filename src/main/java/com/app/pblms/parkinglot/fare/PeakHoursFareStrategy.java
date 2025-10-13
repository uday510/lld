package com.app.pblms.parkinglot.fare;

import com.app.patterns.structural.proxy.ProxyImage;
import com.app.pblms.parkinglot.ticket.Ticket;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Applies a multiplier during peak hours.
 */
public class PeakHoursFareStrategy implements FareStrategy {

    private static final BigDecimal PEAK_MULTIPLIER = new BigDecimal("1.5");

    @Override
    public BigDecimal calculateFare(Ticket ticket, BigDecimal currentFare) {
        LocalDateTime entryTime = ticket.getEntryTime();

        if (isPeakHour(entryTime)) {
            return currentFare.multiply(PEAK_MULTIPLIER);
        }
        return currentFare;
    }

    private boolean isPeakHour(LocalDateTime time) {
        int hour = time.getHour();
        return (hour >= 7 && hour <= 10) || (hour >= 16 && hour <= 19);
    }
}
