package com.app.pblms.movie_ticket_booking;

import com.app.pblms.movie_ticket_booking.seat.Seat;
import com.app.pblms.movie_ticket_booking.seat.SeatType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Represents a Screen (auditorium) in a theatre.
 */
public class Screen {
    private final String name;
    private final Map<String, Seat> seatMap;
    private final Map<Integer, Map<Integer, Seat>> seatsByIndex;
    private final List<Show> shows;
    private final int numRows;
    private final int numCols;

    public Screen(String name, int numRows, int numCols) {
        this.name = name;
        this.numRows = numRows;
        this.numCols = numCols;
        this.seatMap = new ConcurrentHashMap<>();
        this.seatsByIndex = new ConcurrentHashMap<>();
        this.shows = Collections.synchronizedList(new ArrayList<>());
    }

    public void addSeat(int row, int col, Seat seat) {
        if (seat == null) {
            throw new RuntimeException("Seat cannot it null");
        }

        seatsByIndex.putIfAbsent(row, new ConcurrentHashMap<>());
        seatsByIndex.get(row).put(col, seat);
        seatMap.put(seat.getSeatNumber(), seat);
    }

    public void generateSeats(Map<SeatType, Integer> seatTypeRowsMap) {
        int currentRow = 1;
        for (Map.Entry<SeatType, Integer> entry : seatTypeRowsMap.entrySet()) {
            SeatType type = entry.getKey();
            int rowsForType = entry.getValue();

            for (int i = 0; i < rowsForType && currentRow <= numRows; i++) {
                char rowLetter = (char) ('A' + currentRow - 1);

                for (int col = 1; col <= numCols; col++) {
                    String seatNumber = rowLetter + "" + col;
                    Seat seat = type.createSeat(seatNumber);
                    addSeat(currentRow, col, seat);
                }

                currentRow++;
            }
        }
    }

    public Map<String, Seat> getSeatMap() {
        return Collections.unmodifiableMap(seatMap);
    }

    public void printSeatLayout() {
        System.out.println("Seat Layout for " + name + ":");
        for (int row = 1; row <= numRows; row++) {
            if (!seatsByIndex.containsKey(row)) continue;
            System.out.print("Row " + row + ": ");
            for (int col = 1; col <= numCols; col++) {
                Seat seat = seatsByIndex.get(row).get(col);
                if (seat != null) {
                    System.out.print(seat.getSeatNumber() + "(" + seat.getSeatType() + ")  ");
                } else {
                    System.out.print("--  ");
                }
            }
            System.out.println();
        }
    }

    public String getName() { return name; }

    @Override
    public String toString() {
        return STR."Screen{name='\{name}', totalSeats=\{seatMap.size()}, rows=\{numRows}, cols=\{numCols}}";
    }
}
