package com.app.pblms.movie_ticket_booking.entities;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import com.app.pblms.movie_ticket_booking.enums.BookingStatus;

public class Booking {

    private final String id;
    private final User user;
    private final Show show;
    private final List<ShowSeat> seats;
    private final BigDecimal totalAmount;
    private final Instant createdAt;
    private BookingStatus status;

    public Booking(User user, Show show, List<ShowSeat> seats) {
        this.id = UUID.randomUUID().toString();
        this.user = user;
        this.show = show;
        this.seats = List.copyOf(seats);            // immutable defensive copy
        this.totalAmount = seats.stream()
                .map(ShowSeat::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        this.createdAt = Instant.now();
        this.status = BookingStatus.CONFIRMED;
    }

    public void markCancelled()         { this.status = BookingStatus.CANCELLED; }

    public String getId()               { return id; }
    public User getUser()               { return user; }
    public Show getShow()               { return show; }
    public List<ShowSeat> getSeats()    { return seats; }
    public BigDecimal getTotalAmount()  { return totalAmount; }
    public Instant getCreatedAt()       { return createdAt; }
    public BookingStatus getStatus()    { return status; }

}
