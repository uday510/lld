package com.app.pblms.movie_ticket_booking.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import com.app.pblms.movie_ticket_booking.entities.Booking;
import com.app.pblms.movie_ticket_booking.entities.Show;
import com.app.pblms.movie_ticket_booking.entities.ShowSeat;
import com.app.pblms.movie_ticket_booking.entities.User;
import com.app.pblms.movie_ticket_booking.enums.SeatStatus;

public class BookingService {

    // ============================================================
    // APPROACH A — per-show lock: verify-all-then-book-all, no rollback needed
    // ============================================================
    public Booking bookWithLock(Show show, User user, List<String> seatIds) {
        List<ShowSeat> seats = resolve(show, seatIds);

        ReentrantLock lock = show.getLock();
        lock.lock();
        try {
            // verify all available (inside the loc => atomic)
            for (ShowSeat seat : seats) {
                if (seat.getStatus() != SeatStatus.AVAILABLE) {
                    throw new IllegalStateException("Seat " + seat.getId() + " is not available");
                }
            }

            // book all (inside the lock => atomic)
            for (ShowSeat seat : seats) {
                seat.setStatus(SeatStatus.BOOKED);
            }

            return new Booking(user, show, seats);
        } finally {
            lock.unlock();
        }

    }

    // ============================================================
    // APPROACH B — lock-free CAS: book each atomically, rollback on any failure
    // ============================================================
    public Booking bookWithCAS(Show show, User user, List<String> seatIds) {
        List<ShowSeat> seats = resolve(show, seatIds);

        List<ShowSeat> booked = new ArrayList<>();
        for (ShowSeat seat : seats) {
            if (seat.book()) {
                booked.add(seat);
            } else {
                booked.forEach(s -> s.release());
                throw new IllegalStateException("Seat " + seat.getId() + " is not available");
            }
        }
        
        return new Booking(user, show, seats);
    }

    private List<ShowSeat> resolve(Show show, List<String> seatIds) {
        List<ShowSeat> seats = new ArrayList<>();
        for (String id : seatIds) {
            seats.add(show.getShowSeat(id));        // throws IllegalArgumentException if seatId is missing
        }
        return seats;
    }

}
