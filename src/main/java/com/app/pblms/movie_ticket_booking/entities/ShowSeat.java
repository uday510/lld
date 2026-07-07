package com.app.pblms.movie_ticket_booking.entities;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicReference;

import com.app.pblms.movie_ticket_booking.enums.SeatStatus;

public class ShowSeat {

    private final String id;
    private final Seat seat;
    private final AtomicReference<SeatStatus> status =
            new AtomicReference<>(SeatStatus.AVAILABLE);

    public ShowSeat(String showId, Seat seat) {
        this.id = showId + ":" + seat.getId();
        this.seat = seat;
    }

    // ---- Approach A (lock): plain read + set, safe only under the show lock ----
    public SeatStatus getStatus() {
        return status.get();
    }

    public void setStatus(SeatStatus status) {
        this.status.set(status);
    }

    // ---- Approach B (lock-free CAS): atomic transitions ----
    public boolean book() {
        return status.compareAndSet(SeatStatus.AVAILABLE, SeatStatus.BOOKED);
    }
    public void release() {
        status.compareAndSet(SeatStatus.BOOKED, SeatStatus.AVAILABLE);
    }

    public String getId() {
        return id;
    }
    public BigDecimal getPrice() {
        return seat.getCategory().getBasePrice();
    }

}
