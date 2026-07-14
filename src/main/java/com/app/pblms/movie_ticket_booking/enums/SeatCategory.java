package com.app.pblms.movie_ticket_booking.enums;

import java.math.BigDecimal;

public enum SeatCategory {

    SILVER(new BigDecimal("150")),
    GOLD(new BigDecimal("250")),
    PREMIUM(new BigDecimal("400"));

    private final BigDecimal basePrice;

    SeatCategory(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }
}
