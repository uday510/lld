package com.app.pblms.movie_ticket_booking;

import com.app.pblms.movie_ticket_booking.booking.Booking;
import com.app.pblms.movie_ticket_booking.booking.BookingService;
import com.app.pblms.movie_ticket_booking.booking.SeatLockService;
import com.app.pblms.movie_ticket_booking.seat.SeatType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MovieTicketBooking {

    static void main() {

        Movie movie = new Movie("Inception", "Sci-Fi", "2h 28m");

        Screen screen = new Screen("PVR IMAX", 5, 5);
        Map<SeatType, Integer> seatLayout = Map.of(
                SeatType.SILVER, 2,
                SeatType.GOLD, 2,
                SeatType.PLATINUM, 1
        );

        screen.generateSeats(seatLayout);
        screen.printSeatLayout();

        Show show = new Show(
                movie,
                screen,
                Language.ENGLISH,
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(3)
        );

        System.out.println("\nShow create: " + show);

        SeatLockService seatLockService = new SeatLockService(10);
        BookingService bookingService = new BookingService(seatLockService);

        User user = new User(UUID.randomUUID().toString(), "user-1", "user1@email.com");

        List<String> seatsToBook = List.of("A1", "A2");

        System.out.println("\nAttempting to create booking...");
        Booking booking = bookingService.createBooking(show, seatsToBook, user.getUserId());
        System.out.println("\nBooking created: " + booking);

        System.out.println("\n Payment successful! Confirming booking...");
        bookingService.confirmBooking(booking.getBookingId());
        System.out.println("\nBooking confirmed.");

        System.out.println("\nFinal Booking Summary:" + booking);

        System.out.println("\n Booked success. Thank You!");
    }
}
