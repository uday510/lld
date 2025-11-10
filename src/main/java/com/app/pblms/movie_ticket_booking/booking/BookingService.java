package com.app.pblms.movie_ticket_booking.booking;

import com.app.pblms.movie_ticket_booking.Show;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class BookingService {

    private final SeatLockService seatLockService;
    private final Map<String, Booking> bookings;

    public BookingService(SeatLockService seatLockService) {
        this.seatLockService = seatLockService;
        this.bookings = new ConcurrentHashMap<>();
    }

    public synchronized Booking createBooking(Show show, List<String> seatNumbers, String userId) {
        for (String seat : seatNumbers) {
            if (!show.getAvailableSeats().contains(seat)) {
                throw new RuntimeException("Seat " + seat + " is not available");
            }
        }

        for (String seat: seatNumbers) {
            boolean locked = seatLockService.lockSeat(show, seat, userId);
            if (!locked) {
                seatNumbers.forEach(s -> seatLockService.unlockSeat(show, s, userId));
                throw new RuntimeException("Seat " + seat + " could not be locked. Try again.");
            }
        }

        Booking booking = new Booking(UUID.randomUUID().toString(), show, seatNumbers, userId, BookingStatus.PENDING, LocalDateTime.now());
        bookings.put(booking.getBookingId(), booking);
        return booking;
    }

    public synchronized void confirmBooking(String bookingId) {
        Booking booking = bookings.get(bookingId);
        if (booking == null) throw new RuntimeException("Invalid booking");

        Show show = booking.getShow();

        for (String seat : booking.getSeatNumbers()) {
            if (!seatLockService.validateLock(show, seat, booking.getUserId())) {
                throw new RuntimeException("Seat " + seat + " lock expired or invalid");
            }
            show.bookSeat(seat);
            seatLockService.unlockSeat(show, seat, booking.getUserId());
        }

        booking.setStatus(BookingStatus.CONFIRMED);
        System.out.println(" Booking confirmed for " + booking.getSeatNumbers());

    }

    public synchronized void cancelBooking(String bookingId) {
        Booking booking = bookings.get(bookingId);
        if (booking == null) throw new RuntimeException("Invalid booking ID");

        if (booking.getStatus() == BookingStatus.CONFIRMED) {
            for (String seat : booking.getSeatNumbers()) {
                booking.getShow().unlockSeat(seat);
            }
        }

        booking.setStatus(BookingStatus.CANCELLED);
        System.out.println("Booking cancelled: " + bookingId);
    }

    public Map<String, Booking> getAllBookings() {
        return Collections.unmodifiableMap(bookings);
    }
}
