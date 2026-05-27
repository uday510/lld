package com.app.pblms.misc.ratelimiter;

public enum UserTier {
    FREE(10, 10.0),
    PRO         (100, 100.0),
    ENTERPRISE  (1000, 1000.0);

    final int capacity;
    final double refillRatePerSec;

    UserTier(int capacity, double refillRatePerSec) {
        this.capacity = capacity;
        this.refillRatePerSec = refillRatePerSec;
    }

}
