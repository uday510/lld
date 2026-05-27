package com.app.pblms.misc.ratelimiter;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class TokenBucketRateLimiter implements RateLimiter {

    private final Map<String, TokenBucket> buckets = new ConcurrentHashMap<>();
    private final UserTierService tierService;


    public TokenBucketRateLimiter(UserTierService tierService) {
        this.tierService = Objects.requireNonNull(tierService, "tierService");
    }

    @Override
    public boolean allow(String userId) {
        Objects.requireNonNull(userId, "userId");
        TokenBucket bucket = buckets.computeIfAbsent(userId, this::createBucketForUser);
        return bucket.tryConsume();
    }

    private TokenBucket createBucketForUser(String userId) {
        UserTier tier = tierService.getTier(userId);
        return new TokenBucket(tier.capacity, tier.refillRatePerSec);
    }
}
