package com.app.pblms.parkinglot.fare;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PeakHoursFareStrategy implements FareStrategy {

    private static final BigDecimal PEAK_HOURS_MULTIPLIER = new BigDecimal("1.5");

    public PeakHoursFareStrategy() { }

    @Override
    public BigDecimal calculateFare(Ticket ticket, BigDecimal baseFar) {
        BigDecimal fare = baseFar;
        if (isPeakHour(ticket.getEntryTime())) {
            fare = fare.multiply(PEAK_HOURS_MULTIPLIER);
        }
        return fare;
    }

    private boolean isPeakHour(LocalDateTime time) {
        int hr = time.getHour();

        return (hr >= 7 && hr <= 10) || (hr >= 16 && hr <= 19);
    }
}
