package com.app.pblms.movie_ticket_booking.seat;

import java.math.BigDecimal;

abstract public class Seat {
    protected final String seatNumber;
    protected SeatStatus status;
    protected final SeatType seatType;

    public Seat(String seatNumber, SeatType seatType) {
        this.seatNumber = seatNumber;
        this.seatType = seatType;
        this.status = SeatStatus.AVAILABLE;
    }

    public String getSeatNumber() { return seatNumber; }
    public SeatStatus getStatus() { return status; }
    public void setStatus(SeatStatus status) { this.status = status; }
    public SeatType getSeatType() { return seatType; }

    public abstract BigDecimal getPrice();

    @Override
    public String toString() {
        return seatType + "{" +
                "seatNumber='" + seatNumber + '\'' +
                ", status=" + status +
                ", price=" + getPrice() + '}';
    }

}
