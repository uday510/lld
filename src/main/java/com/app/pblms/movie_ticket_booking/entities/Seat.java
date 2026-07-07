package com.app.pblms.movie_ticket_booking.entities;
import com.app.pblms.movie_ticket_booking.enums.SeatCategory;

public class Seat {

    private final String id;
    private final SeatCategory seatCategory;

    public Seat(String id, SeatCategory seatCategory) {
        this.id = id;
        this.seatCategory = seatCategory;
    }

    public String getId() {
        return id;
    }

    public SeatCategory getCategory() {
        return seatCategory;
    }
}
