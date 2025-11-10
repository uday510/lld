package com.app.pblms.movie_ticket_booking.seat;

import java.math.BigDecimal;

public class PlatinumSeat extends Seat {
    public PlatinumSeat(String seatNumber) { super(seatNumber, SeatType.PLATINUM); }

    @Override
    public BigDecimal getPrice() {
        return new BigDecimal("200.0");
    }

}
