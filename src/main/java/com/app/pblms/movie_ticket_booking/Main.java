package com.app.pblms.movie_ticket_booking;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

enum SeatCategory {
    SILVER (new BigDecimal("150")),
    GOLD (new BigDecimal("250")),
    PREMIUM (new BigDecimal("400"));

    final BigDecimal basePrice;
    SeatCategory (BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

}

enum SeatStatus { AVAILABLE, HELD, BOOKED }

class User {
    private final String id, name, email;
    public User (String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User u)) return false;
        return id.equals(u.id);
    }

    public int hashCode() {
        return id.hashCode();
    }
}

class Movie {
    private final String id, title, language;
    private final int durationMins;

    public Movie (String id, String title, String language, int durationMins) {
        this.id = id;
        this.title = title;
        this.language = language;
        this.durationMins = durationMins;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getLanguage() {
        return language;
    }

    public int getDurationMins() {
        return durationMins;
    }
}

class City {
    private final String id, name;
    public City (String id, String name) {
        this.id = id;
        this.name = name;
    }
}

class Seat {
    private final String id;
    private final int row, column;
    private final SeatCategory category;
    public Seat(String id, int row, int column, SeatCategory category) {
        this.id = id;
        this.row = row;
        this.column = column;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public SeatCategory getCategory() {
        return category;
    }
}

class Screen {
    private final String id, name;
    private final List<Seat> seats;

    public Screen(String id, String name, List<Seat> seats) {
        this.id = id;
        this.name = name;
        this.seats = seats;
    }
    public List<Seat> getSeats() {
        return seats;
    }
}

class Cinema {
    private final String id, name;
    private final City city;
    private final List<Screen> screens = new ArrayList<>();
    public Cinema (String id, String name, City city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }
    public void addScreen(Screen screen) {
        screens.add(screen);
    }

}

class ShowSeat {
    private final String id;
    private final String showId;
    private final Seat seat;

    private SeatStatus status = SeatStatus.AVAILABLE;
    private User heldBy;
    private Instant heldUntil;

    public ShowSeat (String showId, Seat seat) {
        this.showId = showId;
        this.id = showId + ":" + seat.getId();
        this.seat = seat;
    }

    public SeatStatus getStatus() {
//        if (status == SeatStatus.HELD && he)
        return null;
    }
}