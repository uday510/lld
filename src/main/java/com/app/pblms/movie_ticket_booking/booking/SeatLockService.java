package com.app.pblms.movie_ticket_booking.booking;

import com.app.pblms.movie_ticket_booking.Show;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SeatLockService {

    private final Map<Show, Map<String, SeatLock>> locksByShow;
    private final long lockTimeoutSeconds;

    public SeatLockService(long lockTimeoutSeconds) {
        this.locksByShow = new ConcurrentHashMap<>();
        this.lockTimeoutSeconds = lockTimeoutSeconds;
    }

    public synchronized boolean lockSeat(Show show, String seatNumber, String userId) {
        Map<String, SeatLock> lockForShow = locksByShow.computeIfAbsent(show, k-> new ConcurrentHashMap<>());

        SeatLock existingLock = lockForShow.get(seatNumber);
        if (existingLock != null && !existingLock.isExpired()) {
            return false;
        }

        lockForShow.put(seatNumber, new SeatLock(userId, LocalDateTime.now().plusSeconds(lockTimeoutSeconds)));
        show.lockSeat(seatNumber);
        return true;
    }

    public synchronized void unlockSeat(Show show, String seatNumber, String userId) {
        Map<String, SeatLock> locksForShow = locksByShow.get(show);
        if (locksForShow == null) return;

        SeatLock lock = locksForShow.get(seatNumber);
        if (lock != null && lock.getUserId().equals(userId)) {
            locksForShow.remove(seatNumber);
            show.unlockSeat(seatNumber);
        }

    }

    public boolean validateLock(Show show, String seatNumber, String userId) {
        Map<String, SeatLock> locksForShow = locksByShow.get(show);
        if (locksForShow == null) return false;

        SeatLock lock = locksForShow.get(seatNumber);
        return lock != null && lock.getUserId().equals(userId) && !lock.isExpired();
    }

    public void cleanExpiredLocks() {
        for (Show show : locksByShow.keySet()) {
            Map<String, SeatLock> locksForShow = locksByShow.get(show);
            locksForShow.entrySet().removeIf(e -> e.getValue().isExpired());
        }
    }

}
