package app;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

interface PricingStrategy {
    BigDecimal getPrice();
}

abstract class BaseRate implements PricingStrategy {
    final private BigDecimal price;

    public BaseRate(BigDecimal price) {
        this.price = price;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }
}

class NormalRate extends BaseRate {
    public NormalRate(BigDecimal price) { super(price); }
}

class PremiumRate extends BaseRate {
    public PremiumRate(BigDecimal price) { super(price); }
}

class VIPRate extends BaseRate {
    public VIPRate(BigDecimal price) { super(price); }
}

class Seat {
    final private String seatNumber;
    private PricingStrategy pricingStrategy;

    Seat(String seatNumber, PricingStrategy pricingStrategy) {
        this.seatNumber = seatNumber;
        this.pricingStrategy = pricingStrategy;
    }

    public PricingStrategy getPricingStrategy() {
        return pricingStrategy;
    }

    public void setPricingStrategy(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }
}

class Layout {
    private final int rows;
    private final int cols;
    private final Map<String, Seat> seatsByNumber;
    private final Map<Integer, Map<Integer, Seat>> seatsByPosition;

    Layout(int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("Rows and columns must be positive.");
        }

        this.rows = rows;
        this.cols = cols;
        this.seatsByNumber = new HashMap<>();
        this.seatsByPosition = new HashMap<>();

        for (int row = 1; row <= rows; row++) {
            seatsByPosition.put(row, new HashMap<>(cols));
        }
    }

    void addSeat(int row, int col, String seatNumber, Seat seat) {
        if (row <= 0 || row > rows) {
            throw new IllegalArgumentException("Invalid row number: " + row);
        }
        if (col <= 0 || col > cols) {
            throw new IllegalArgumentException("Invalid column number: " + col);
        }
        if (seatsByNumber.containsKey(seatNumber)) {
            throw new IllegalArgumentException("Seat number already exists: " + seatNumber);
        }

        Map<Integer, Seat> seatsRow = seatsByPosition.get(row);
        if (seatsRow.get(col) != null) {
            throw new IllegalArgumentException("Seat already exists at row " + row + ", col " + col);
        }

        seatsRow.put(col, seat);
        seatsByNumber.put(seatNumber, seat);
    }

    Seat getSeatByPosition(int row, int col) {
        Map<Integer, Seat> seatsRow = seatsByPosition.get(row);
        return (seatsRow != null) ? seatsRow.get(col) : null;
    }

    Seat getSeatByNumber(String seatNumber) {
        return seatsByNumber.get(seatNumber);
    }
}


class Room {
    final int roomNumber;
    final Layout layout;

    Room (int roomNumber, Layout layout) {
        this.roomNumber = roomNumber;
        this.layout = layout;
    }

    @Override
    public String toString() {
        return "Room " + roomNumber;
    }

    public Layout getLayout() {
        return layout;
    }
}

class Cinema {
    private final String name;
    private final String location;
    private final List<Room> rooms;

    public Cinema(String name, String location, List<Room> rooms) {
        this.name = name;
        this.location = location;
        this.rooms = rooms;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public String getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }
}

class Movie {
    private final String title;
    private final String genre;
    private final int durationInMinutes;

    public Movie(String title, String genre, int durationInMinutes) {
        this.title = title;
        this.genre = genre;
        this.durationInMinutes = durationInMinutes;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }
}

class Screening {
    private Movie movie;
    private final Room room;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    Screening(Movie movie, Room room, LocalDateTime startTime) {
        this.movie = movie;
        this.room = room;
        this.startTime = this.startTime;
        this.endTime = this.startTime.plusMinutes(movie.getDurationInMinutes());
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Room getRoom() {
        return room;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}

class Ticket {
    Screening screening;
    Seat seat;
    BigDecimal price;
}
class ScreeningManager {
    private final Map<Movie, List<Screening>> screeningsByMovie;
    private final Map<Screening, List<Ticket>>
}


public class Solution {
}
