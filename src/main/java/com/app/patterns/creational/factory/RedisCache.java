package com.app.patterns.creational.factory;

import javax.swing.*;
import java.time.Duration;
import java.util.Optional;

public class RedisCache implements Cache {
    private final RedisClient client;
    private final String keyPrefix;

    public RedisCache(RedisClient client, String keyPrefix) {
        this.client = client;
        this.keyPrefix = keyPrefix;
    }

    @Override
    public Optional<String> get(String key) {
        String raw = client.get(prefixed(key));
        return Optional.ofNullable(raw);
    }

    @Override
    public void delete(String key) {
        client.del(prefixed(key));
    }

    @Override
    public void put(String key, String value, Duration ttl) {
        client.setEx(prefixed(key), ttl.getSeconds(), value);
    }

    private String prefixed(String key) {
        return keyPrefix + ":" + key;
    }
}
