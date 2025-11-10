package com.app.pblms.movie_ticket_booking.seat;

import java.math.BigDecimal;

public class GoldSeat extends Seat {
    public GoldSeat(String seatNumber) { super(seatNumber, SeatType.GOLD); }

    @Override
    public BigDecimal getPrice() {
        return new BigDecimal("150.0");
    }

}
