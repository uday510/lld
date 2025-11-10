package com.app.pblms.movie_ticket_booking.booking;

import java.time.LocalDateTime;

public class SeatLock {
    private final String userId;
    private final LocalDateTime expiryTime;

    public SeatLock(String userId, LocalDateTime expiryTime) {
        this.userId = userId;
        this.expiryTime = expiryTime;
    }

    public String getUserId() { return userId; }
    public boolean isExpired() { return LocalDateTime.now().isAfter(expiryTime); }

    @Override
    public String toString() {
        return "SeatLock{" + "userId='" + userId + '\'' + ", expiresAt=" + expiryTime + '}';
    }
}
