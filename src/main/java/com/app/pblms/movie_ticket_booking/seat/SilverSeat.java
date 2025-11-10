package com.app.pblms.movie_ticket_booking.seat;

import java.math.BigDecimal;

public class SilverSeat extends Seat {
    public SilverSeat(String seatNumber) { super(seatNumber, SeatType.SILVER); }

    @Override
    public BigDecimal getPrice() {
        return new BigDecimal("100.0");
    }

}
