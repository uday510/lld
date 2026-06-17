package com.app.pblms.ratelimiter;

import java.util.concurrent.locks.ReentrantLock;

public class TokenBucket {

    private final int capacity;
    private final double refillRatePerSec;

    private double tokens;
    private long lastRefillNanos;

    private final ReentrantLock lock = new ReentrantLock();

    TokenBucket(int capacity, double refillRatePerSec) {
        this.capacity = capacity;
        this.refillRatePerSec = refillRatePerSec;
        this.tokens = capacity;
        this.lastRefillNanos = System.nanoTime();
    }

    boolean tryConsume() {
        lock.lock();
        try {
            refill();
            if (tokens >= 1.0) {
                tokens -= 1.0;
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    private void refill() {
        long now = System.nanoTime();
        double elapsedSeconds = (now - lastRefillNanos) / 1_000_000_000.0;
        double tokensToAdd = elapsedSeconds * refillRatePerSec;

        if (tokensToAdd > 0) {
            tokens = Math.min(capacity, tokens + tokensToAdd);
            lastRefillNanos = now;
        }

    }
}
