package com.app.patterns.creational.factory;

import java.time.Duration;
import java.util.Optional;

public class MemcachedCache implements Cache {
    private final MemcachedClient client;
    private final String keyPrefix;

    public MemcachedCache(MemcachedClient client, String keyPrefix) {
        this.client = client;
        this.keyPrefix = keyPrefix;
    }

    @Override
    public Optional<String> get(String key) {
        Object raw = client.get(prefixed(key));
        return Optional.ofNullable((String) raw);
    }

    @Override
    public void delete(String key) {
        client.delete(prefixed(key));
    }

    @Override
    public void put(String key, String value, Duration ttl) {
        client.set(prefixed(key), (int) ttl.getSeconds(), value);
    }

    private String prefixed(String key) {
        return keyPrefix + ":" + key;
    }
}
