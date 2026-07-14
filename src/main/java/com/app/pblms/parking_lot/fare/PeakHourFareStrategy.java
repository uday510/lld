package com.app.pblms.parking_lot.fare;

import com.app.pblms.parking_lot.ticket.Ticket;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class PeakHourFareStrategy implements FareStrategy {

    private static final BigDecimal PEAK_MULTIPLIER = new BigDecimal("1.5");

    public BigDecimal calculateFare(Ticket ticket, BigDecimal currentFare) {
        Instant entryTime = ticket.getEntryTime();

        if (isPeakHour(entryTime)) {
            return currentFare.multiply(PEAK_MULTIPLIER);
        }

        return currentFare;
    }

    private boolean isPeakHour(Instant time) {

        ZoneId zone = ZoneId.systemDefault();

        long hour =  time.atZone(zone).getHour();

        boolean isMorning = (hour >= 8 && hour < 10);
        boolean isEvening = (hour >= 17 && hour < 19);

        return isMorning || isEvening;
    }

}
