package com.app.pblms.misc.ratelimiter;

public interface RateLimiter {
    boolean allow(String userId);
}
