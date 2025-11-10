package com.app.pblms.movie_ticket_booking.seat;

@FunctionalInterface
public interface SeatFactory {
    Seat create(String seatNumber);
}
