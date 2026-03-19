package com.app.pblms.movie_ticket_booking;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

// ENUMS

enum SeatType { REGULAR, PREMIUM, RECLINER }

enum SeatStatus { AVAILABLE, LOCKED, BOOKED }

enum BookingStatus { CREATED, CONFIRMED, FAILED, CANCELLED }

enum PaymentStatus { INITIATED, SUCCESS, FAILED }

// DOMAIN CLASSES

class Movie {
    private final String id;
    private final String title;

    public Movie(String id, String title) {
        this.id = id;
        this.title = title;
    }
}

class Seat {
    private final String id;
    private final int row;
    private final int number;
    private final SeatType type;

    public Seat(String id, int row, int number, SeatType type) {
        this.id = id;
        this.row = row;
        this.number = number;
        this.type = type;
    }

    public String getId() { return id; }
}

class Screen {
    private final String id;
    private final List<Seat> seats;

    public Screen(String id, List<Seat> seats) {
        this.id = id;
        this.seats = seats;
    }

    public List<Seat> getSeats() { return seats; }
}

class ShowSeat {
    private final String seatId;
    private SeatStatus status;
    private final double price;

    public ShowSeat(String seatId, double price) {
        this.seatId = seatId;
        this.price = price;
        this.status = SeatStatus.AVAILABLE;
    }

    public synchronized boolean isAvailable() {
        return status == SeatStatus.AVAILABLE;
    }

    public synchronized void lock() {
        status = SeatStatus.LOCKED;
    }

    public synchronized void book() {
        status = SeatStatus.BOOKED;
    }

    public synchronized void release() {
        status = SeatStatus.AVAILABLE;
    }

    public String getSeatId() { return seatId; }
    public double getPrice() { return price; }
}

class Show {
    private final String id;
    private final Movie movie;
    private final Screen screen;
    private final Map<String, ShowSeat> showSeats;

    public Show(String id, Movie movie, Screen screen) {
        this.id = id;
        this.movie = movie;
        this.screen = screen;
        this.showSeats = new HashMap<>();

        for (Seat seat : screen.getSeats()) {
            showSeats.put(seat.getId(), new ShowSeat(seat.getId(), 100));
        }
    }

    public ShowSeat getShowSeat(String seatId) {
        return showSeats.get(seatId);
    }
}

class User {
    private final String id;

    public User(String id) {
        this.id = id;
    }

    public String getId() { return id; }
}

class Booking {
    private final String id;
    private final User user;
    private final Show show;
    private final List<ShowSeat> seats;
    private BookingStatus status;
    private final String idempotencyKey;


    public Booking(String id, User user, Show show, List<ShowSeat> seats, String idempotencyKey) {
        this.id = id;
        this.user = user;
        this.show = show;
        this.seats = seats;
        this.status = BookingStatus.CREATED;
        this.idempotencyKey = idempotencyKey;
    }

    public List<ShowSeat> getSeats() { return seats; }

    public void confirm() { this.status = BookingStatus.CONFIRMED; }
    public void fail() { this.status = BookingStatus.FAILED; }
}

class Payment {
    private final String id;
    private final double amount;
    private PaymentStatus status;

    public Payment(String id, double amount) {
        this.id = id;
        this.amount = amount;
        this.status = PaymentStatus.INITIATED;
    }

    public void markSuccess() { status = PaymentStatus.SUCCESS; }
    public void markFailed() { status = PaymentStatus.FAILED; }

    public PaymentStatus getStatus() { return status; }
}

// SERVICES

class SeatLockService {

    private final Map<String, LockInfo> locks = new ConcurrentHashMap<>();
    private static final long TIMEOUT_MS = 300_000;

    public boolean lockSeats(List<ShowSeat> seats, String userId) {

        long now = System.currentTimeMillis();

        // Step 1: Validate all seats
        for (ShowSeat seat : seats) {
            if (!seat.isAvailable() || isLocked(seat.getSeatId(), now)) {
                return false;
            }
        }

        // Step 2: Lock all seats (atomic at application level)
        long expiry = now + TIMEOUT_MS;

        for (ShowSeat seat : seats) {
            seat.lock();
            locks.put(seat.getSeatId(), new LockInfo(userId, expiry));
        }

        return true;
    }

    public void unlockSeats(List<ShowSeat> seats, String userId) {
        for (ShowSeat seat : seats) {
            LockInfo lock = locks.get(seat.getSeatId());

            if (lock != null && lock.userId.equals(userId)) {
                seat.release();
                locks.remove(seat.getSeatId());
            }
        }
    }

    private boolean isLocked(String seatId, long now) {
        LockInfo lock = locks.get(seatId);

        if (lock == null) return false;

        if (now > lock.expiryTime) {
            locks.remove(seatId); // expire
            return false;
        }

        return true;
    }

    static class LockInfo {
        String userId;
        long expiryTime;

        LockInfo(String userId, long expiryTime) {
            this.userId = userId;
            this.expiryTime = expiryTime;
        }
    }
}

class PaymentService {

    private final Map<String, Payment> paymentStore = new ConcurrentHashMap<>();

    public Payment processPayment(String idempotencyKey, double amount) {

        // Check duplicate request
        if (paymentStore.containsKey(idempotencyKey)) {
            return paymentStore.get(idempotencyKey);
        }

        Payment payment = new Payment(
                UUID.randomUUID().toString(),
                amount
        );

//        boolean success = externalGatewayCall();
        boolean success = true;

        if (success) payment.markSuccess();
        else payment.markFailed();

        paymentStore.put(idempotencyKey, payment);

        return payment;
    }
}
class IdempotencyStore {

    private final Map<String, Booking> store = new ConcurrentHashMap<>();

    public Booking get(String key) {
        return store.get(key);
    }

    public void put(String key, Booking booking) {
        store.put(key, booking);
    }
}

class BookingService {

    private final IdempotencyStore idempotencyStore;
    private final SeatLockService lockService;
    private final PaymentService paymentService;

    public BookingService(IdempotencyStore store,
                          SeatLockService lockService,
                          PaymentService paymentService) {
        this.idempotencyStore = store;
        this.lockService = lockService;
        this.paymentService = paymentService;
    }

    public Booking createBooking(User user,
                                 Show show,
                                 List<String> seatIds,
                                 String idempotencyKey) {

        // 1. Idempotency check
        Booking existing = idempotencyStore.get(idempotencyKey);
        if (existing != null) return existing;

        // 2. Fetch seats
        List<ShowSeat> seats = fetchSeats(show, seatIds);

        // 3. Lock seats
        if (!lockService.lockSeats(seats, user.getId())) {
            throw new RuntimeException("Seats not available");
        }

        // 4. Create booking
        Booking booking = new Booking(
                UUID.randomUUID().toString(),
                user,
                show,
                seats,
                idempotencyKey
        );

        // 5. Process payment (idempotent)
        double total = seats.stream().mapToDouble(ShowSeat::getPrice).sum();
        Payment payment = paymentService.processPayment(idempotencyKey, total);

        if (payment.getStatus() == PaymentStatus.SUCCESS) {
            seats.forEach(ShowSeat::book);
            booking.confirm();
        } else {
            lockService.unlockSeats(seats, user.getId());
            booking.fail();
        }

        // 6. Save idempotent result
        idempotencyStore.put(idempotencyKey, booking);

        return booking;
    }

    private List<ShowSeat> fetchSeats(Show show, List<String> seatIds) {
        List<ShowSeat> seats = new ArrayList<>();
        for (String id : seatIds) {
            seats.add(show.getShowSeat(id));
        }
        return seats;
    }
}

// DEMO

public class Main {
    public static void main(String[] args) {
        List<Seat> seats = List.of(
                new Seat("A1", 1, 1, SeatType.REGULAR),
                new Seat("A2", 1, 2, SeatType.REGULAR)
        );

        Screen screen = new Screen("S1", seats);
        Movie movie = new Movie("M1", "Inception");
        Show show = new Show("SH1", movie, screen);

        SeatLockService lockService = new SeatLockService();
        PaymentService paymentService = new PaymentService();

        IdempotencyStore store = new IdempotencyStore();

        BookingService bookingService =
                new BookingService(store, lockService, paymentService);

        User user = new User("U1");

        String idempotencyKey = UUID.randomUUID().toString();

        Booking booking = bookingService.createBooking(
                user,
                show,
                List.of("A1"),
                idempotencyKey
        );

        System.out.println("Booking completed");
    }
}
