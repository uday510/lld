package com.app.pblms.movie_ticket_booking.seat;

public enum SeatType {
    SILVER(SilverSeat::new),
    GOLD(GoldSeat::new),
    PLATINUM(PlatinumSeat::new);

    private final SeatFactory factory;

    SeatType(SeatFactory factory) {
        this.factory = factory;
    }

    public Seat createSeat(String seatNumber) {
        return factory.create(seatNumber);
    }

}
