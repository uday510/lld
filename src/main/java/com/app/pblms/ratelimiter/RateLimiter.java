package com.app.pblms.ratelimiter;

public interface RateLimiter {
    boolean allow(String userId);
}
