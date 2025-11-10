package com.app.pblms.movie_ticket_booking.booking;

import com.app.pblms.movie_ticket_booking.Show;

import java.time.LocalDateTime;
import java.util.List;

public class Booking {

    private final String bookingId;
    private final Show show;
    private final List<String> seatNumbers;
    private final String userId;
    private BookingStatus status;
    private final LocalDateTime createdAt;

    public Booking(String bookingId, Show show, List<String> seatNumbers, String userId, BookingStatus status, LocalDateTime createdAt) {
        this.bookingId = bookingId;
        this.show = show;
        this.seatNumbers = seatNumbers;
        this.userId = userId;
        this.status = status;
        this.createdAt = createdAt;

    }

    public String getBookingId() { return bookingId; }
    public Show getShow() { return show; }
    public List<String> getSeatNumbers() { return seatNumbers; }
    public String getUserId() { return userId; }
    public BookingStatus getStatus() { return status; }
    public void setStatus(BookingStatus status) { this.status = status; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    @Override
    public String toString() {
        return STR."Booking{id='\{bookingId}', user='\{userId}', seats=\{seatNumbers}, status=\{status}}";
    }

}
