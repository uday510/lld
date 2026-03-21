package com.app.pblms.misc.movie_ticket_booking;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

enum SeatType { REGULAR, PREMIUM, RECLINER }

enum BookingStatus { CREATED, CONFIRMED, CANCELLED }

class Seat {

    private final String seatId;
    private final SeatType seatType;

    public Seat(String seatId, SeatType seatType) {
        this.seatId = seatId;
        this.seatType = seatType;
    }

    public String getSeatId() { return seatId; }
    public SeatType getSeatType() { return seatType; }

}

class Movie {

    private final String movieId;
    private final String title;
    private final String language;

    public Movie(String movieId, String title, String language) {
        this.movieId = movieId;
        this.title = title;
        this.language = language;
    }

    public String getMovieId() { return movieId; }
    public String getTitle() { return title; }

}

class ShowSeat {

    private final Seat seat;
    private final BigDecimal price;

    private boolean isBooked;
    private long lockExpiryTime;

    private final ReentrantLock lock = new ReentrantLock();

    public ShowSeat(Seat seat, BigDecimal price) {
        this.seat = seat;
        this.price = price;
    }

    public boolean tryReserve(long timeoutMillis) {

        lock.lock();
        try {
            long now = System.currentTimeMillis();

            if (isBooked || lockExpiryTime > now) return false;

            lockExpiryTime = now + timeoutMillis;
            return true;
        } finally {
            lock.unlock();
        }

    }

    public void unlock() {

        lock.lock();
        try {
            lockExpiryTime = 0;
        } finally {
            lock.unlock();
        }

    }

    public void confirmBooking() {

        lock.lock();
        try {
            isBooked = true;
            lockExpiryTime = 0;
        } finally {
            lock.unlock();
        }

    }

    public boolean isAvailable() {

        return !isBooked && lockExpiryTime < System.currentTimeMillis();

    }

    public BigDecimal getPrice() { return price; }
}

class Show {

    private final String showId;
    private final Movie movie;
    private final Date startTime;
    private final Map<String, ShowSeat> showSeats;

    public Show(String showId, Movie movie, Date startTime, Map<String, ShowSeat> showSeats) {

        this.showId = showId;
        this.movie = movie;
        this.startTime = startTime;
        this.showSeats = showSeats;

    }

    public List<ShowSeat> getSeats(List<String> seatIds) {

        if (seatIds == null || seatIds.isEmpty()) {
            throw new IllegalArgumentException("No seats selected");
        }

        return seatIds.stream()
                .map(id -> {
                    ShowSeat seat = showSeats.get(id);
                    if (seat == null) {
                        throw new RuntimeException("Seat " + id + " not found");
                    }
                    return seat;
                })
                .toList();

    }

    public Movie getMovie() { return movie; }

}

class SearchService {

    private final Map<String, Set<Movie>> cityToMovies = new HashMap<>();
    private final Map<String, List<Show>> movieToShows = new HashMap<>();

    public void addShow(String city, Show show) {

        String key = city.toLowerCase();

        cityToMovies
                .computeIfAbsent(key, k -> new HashSet<>())
                .add(show.getMovie());

        movieToShows
                .computeIfAbsent(show.getMovie().getMovieId(), k -> new ArrayList<>())
                .add(show);

    }

    public List<Movie> searchMoviesByCity(String city) {
        return new ArrayList<>(cityToMovies.getOrDefault(city.toLowerCase(), Collections.emptySet()));
    }

    public List<Show> getShowsForMovie(String movieId) {
        return movieToShows.getOrDefault(movieId, Collections.emptyList());
    }

}

class Payment {

    private final String paymentId;
    private final BigDecimal amount;
    private final boolean success;

    public Payment(BigDecimal amount) {
        this.paymentId = UUID.randomUUID().toString();
        this.amount = amount;
        this.success = true; // mock
    }

    public boolean isSuccess() { return success; }

}

class Booking {

    private final String bookingId;
    private final User user;
    private final Show show;
    private final List<ShowSeat> seats;

    private BookingStatus status;
    private Payment payment;

    public Booking(User user, Show show, List<ShowSeat> seats) {
        this.bookingId = UUID.randomUUID().toString();
        this.user = user;
        this.show = show;
        this.seats = seats;
        this.status = BookingStatus.CREATED;
    }

    public BigDecimal calculateTotal() {
        return seats.stream()
                .map(ShowSeat::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void confirm(Payment payment) {
        this.payment = payment;
        this.status = BookingStatus.CONFIRMED;
        seats.forEach(ShowSeat::confirmBooking);
    }

    public void cancel() {
        this.status = BookingStatus.CANCELLED;
        seats.forEach(ShowSeat::unlock);
    }

}

class BookingService {

    private static final long LOCK_TIMEOUT = 5 * 60 * 1000;

    public Booking createBooking(User user, Show show, List<String> seatIds) {
        List<ShowSeat> seats = show.getSeats(seatIds);
        List<ShowSeat> lockedSeats = new ArrayList<>();

        for (ShowSeat seat : seats) {
            if (seat.tryReserve(LOCK_TIMEOUT)) {
                lockedSeats.add(seat);
            } else {
                lockedSeats.forEach(ShowSeat::unlock);
                throw new RuntimeException("Seat not available");
            }
        }

        return new Booking(user, show, seats);
    }

    public void confirmBooking(Booking booking) {
        Payment payment = new Payment(booking.calculateTotal());

        if (payment.isSuccess()) {
            booking.confirm(payment);
        } else {
            booking.cancel();
            throw new RuntimeException("Payment failed");
        }
    }

}

class User {

    private final String userId;
    private final String email;

    public User(String userId, String email) {
        this.userId = userId;
        this.email = email;
    }

}

public class Main {

    public static void main() {

        SearchService searchService = new SearchService();
        BookingService bookingService = new BookingService();

        Movie movie = new Movie("M1", "Interstellar", "English");

        Map<String, ShowSeat> seatMap = new HashMap<>();
        seatMap.put("A1", new ShowSeat(new Seat("A1", SeatType.REGULAR), new BigDecimal("150")));
        seatMap.put("A2", new ShowSeat(new Seat("A2", SeatType.REGULAR), new BigDecimal("150")));

        Show show = new Show("SH1", movie, new Date(), seatMap);
        searchService.addShow("London", show);

        User user1 = new User("U1", "alice@test.com");
        User user2 = new User("U2", "bob@test.com");

        ExecutorService executor = Executors.newFixedThreadPool(2);

        Runnable t1 = () -> {
            try {
                Booking b = bookingService.createBooking(user1, show, List.of("A1"));
                bookingService.confirmBooking(b);
                System.out.println("User1 SUCCESS");
            } catch (Exception e) {
                System.out.println("User1 FAILED: " + e.getMessage());
            }
        };

        Runnable t2 = () -> {
            try {
                Booking b = bookingService.createBooking(user2, show, List.of("A1"));
                bookingService.confirmBooking(b);
                System.out.println("User2 SUCCESS");
            } catch (Exception e) {
                System.out.println("User2 FAILED: " + e.getMessage());
            }
        };

        executor.submit(t1);
        executor.submit(t2);
        executor.shutdown();
    }

}