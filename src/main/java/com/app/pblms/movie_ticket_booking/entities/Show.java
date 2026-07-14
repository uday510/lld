package com.app.pblms.movie_ticket_booking.entities;

import com.app.pblms.movie_ticket_booking.enums.SeatStatus;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class Show {

    private final String id;
    private final Instant startTime;
    private final Map<String, ShowSeat> showSeats = new HashMap<>();    // seatId -> ShowSeat
    private final ReentrantLock lock = new ReentrantLock();            // used by Approach A

    public Show(String id, Instant startTime, List<Seat> seats) {
        this.id = id;
        this.startTime = startTime;
        for (Seat seat : seats) {
            ShowSeat ss = new ShowSeat(id, seat);
            showSeats.put(ss.getId(), ss);
        }
    }

    public String getId() {
        return id;
    }

    public ReentrantLock getLock() {
        return lock;
    }

    public ShowSeat getShowSeat(String seatId) {
        ShowSeat ss = showSeats.get(seatId);
        if (ss == null) {
            throw new IllegalArgumentException("Invalid seatId: " + seatId);
        }
        return ss;
    }

    public List<ShowSeat> getAvailableSeats() {
        List<ShowSeat> out = new ArrayList<>();
        for (ShowSeat ss : showSeats.values()) {
            if (ss.getStatus() == SeatStatus.AVAILABLE) {
                out.add(ss);
            }
        }
        return out;
    }

}
