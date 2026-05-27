package com.app.pblms.misc.ratelimiter;

import java.util.HashMap;
import java.util.Map;

public class StubUserTierService implements UserTierService {

    private final Map<String, UserTier> userTiers = new HashMap<>();

    public StubUserTierService() {
        userTiers.put("free-alice",             UserTier.FREE);
        userTiers.put("pro-bob",                UserTier.PRO);
        userTiers.put("enterprise-charlie",     UserTier.ENTERPRISE);
    }

    @Override
    public UserTier getTier(String userId) {
        return userTiers.getOrDefault(userId, UserTier.FREE);
    }

    public void setTier(String userId, UserTier tier) {
        userTiers.put(userId, tier);
    }
}
