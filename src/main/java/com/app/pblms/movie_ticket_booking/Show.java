package com.app.pblms.movie_ticket_booking;

import com.app.pblms.movie_ticket_booking.seat.Seat;
import com.app.pblms.movie_ticket_booking.seat.SeatStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Show {

    private Movie movie;
    private Screen screen;
    private Language language;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private final Map<String, SeatStatus> seatStatusMap;

    public Show(Movie movie, Screen screen, Language language, LocalDateTime startTime, LocalDateTime endTime) {
        this.movie = movie;
        this.screen = screen;
        this.language = language;
        this.startTime = startTime;
        this.endTime = endTime;

        this.seatStatusMap = new ConcurrentHashMap<>();

        this.initializeSeatStatus();
    }

    private void initializeSeatStatus() {
        for (Seat seat : screen.getSeatMap().values()) {
            seatStatusMap.put(seat.getSeatNumber(), SeatStatus.AVAILABLE);
        }
    }

    public List<String> getAvailableSeats() {
        List<String> available = new ArrayList<>();
        for (Map.Entry<String, SeatStatus> entry : seatStatusMap.entrySet()) {
            if (entry.getValue() == SeatStatus.AVAILABLE) {
                available.add(entry.getKey());
            }
        }

        return available;
    }

    public boolean lockSeat(String seatNumber) {
        return seatStatusMap.replace(seatNumber, SeatStatus.AVAILABLE, SeatStatus.BLOCKED);
    }

    public void bookSeat(String seatNumber) {
        seatStatusMap.put(seatNumber, SeatStatus.RESERVED);
    }

    public void unlockSeat(String seatNumber) {
        if (seatStatusMap.containsKey(seatNumber) && seatStatusMap.get(seatNumber) == SeatStatus.BLOCKED) {
            seatStatusMap.put(seatNumber, SeatStatus.AVAILABLE);
        }
    }

    public Movie getMovie() { return movie; }
    public Screen getScreen() { return screen; }
    public Language getLanguage() { return language; }
    public LocalDateTime getStartTime() { return startTime; }
    public LocalDateTime getEndTime() { return endTime; }

    @Override
    public String toString() {
        return STR."Show{movie='\{movie.getTitle()}', language=\{language}, screen='\{screen.getName()}', start=\{startTime}, end=\{endTime}, availableSeats=\{getAvailableSeats().size()}}";
    }

}
