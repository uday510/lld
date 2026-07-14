package com.app.pblms.movie_ticket_booking;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.app.pblms.movie_ticket_booking.entities.Booking;
import com.app.pblms.movie_ticket_booking.entities.Seat;
import com.app.pblms.movie_ticket_booking.entities.Show;
import com.app.pblms.movie_ticket_booking.entities.User;
import com.app.pblms.movie_ticket_booking.enums.SeatCategory;
import com.app.pblms.movie_ticket_booking.services.BookingService;

public class Main {
    public static void main(String[] args) throws InterruptedException {
       
        List<Seat> seats = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            seats.add(new Seat("A" + i, SeatCategory.GOLD));
        }

        Show show = new Show("s1", Instant.now(), seats);

        BookingService bookingService = new BookingService();
        User alice = new User("u1", "Alice");

        // --- sanity 1: a normal booking works ---
        System.out.println("=== Sanity: book A1, A2 ===");
        Booking b = bookingService.bookWithLock(show, alice, List.of("A1", "A2"));
        System.out.println("Booked " + b.getId() + " total=" + b.getTotalAmount()
                + " status=" + b.getStatus());

        // --- sanity 2: booking an already-booked seat fails ---
        System.out.println("\n=== Sanity: book A1 again (taken) ===");
        try {
            bookingService.bookWithCAS(show, new User("u2", "Bob"), List.of("A1"));
            System.out.println("ERROR: should have failed");
        } catch (Exception e) {
            System.out.println("Expected failure: " + e.getMessage());
        }

        // --- THE RACE TEST: 10 threads all book the SAME free seat A3 ---
        AtomicInteger successes = new AtomicInteger();
        AtomicInteger failures = new AtomicInteger();

        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            final int idx = i;
            threads[i] = new Thread(() -> {
                User u = new User("race-" + idx, "Racer" + idx);
                try {
                    bookingService.bookWithCAS(show, u, List.of("A3"));
                    successes.incrementAndGet();
                } catch (Exception e) {
                    failures.incrementAndGet();
                }
            });
        }

        for (Thread t : threads) t.start();    // launch all
        for (Thread t : threads) t.join();     // wait for all

        System.out.println("Successes: " + successes.get() + "  (must be 1)");
        System.out.println("Failures:  " + failures.get() + "  (must be 9)");

        // --- all-or-nothing test: request [A4, A1] where A1 is taken -> book NONE ---
        System.out.println("\n=== All-or-nothing: [A4, A1], A1 taken ===");
        try {
            bookingService.bookWithCAS(show, alice, List.of("A4", "A1"));
            System.out.println("ERROR: should have failed");
        } catch (Exception e) {
            System.out.println("Expected failure: " + e.getMessage());
            // A4 must still be AVAILABLE — it was rolled back
            System.out.println("A4 status: " + show.getShowSeat("A4").getStatus()
                    + "  (must be AVAILABLE)");
        }
    }
}