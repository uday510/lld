package com.app.pblms.movie_ticket_booking.entities;

public class Show {

    private final String id;
    private final Instant startTime;
    private final Map<String, ShowSeat> showSeats = new HashMap<>();    // seatId -> ShowSeat
    private final RReentrantLock lock = new ReentrantLock();            // used by Approach A

    public Show(String id, Instant startTime, List<Seat> seats) {
        this.id = id;
        this.startTime = startTime;
        for (Seat seat : seats) {
            showSeat ss = new ShowSeat(id, seat);
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
