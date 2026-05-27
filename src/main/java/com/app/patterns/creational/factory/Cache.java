package com.app.patterns.creational.factory;

import java.time.Duration;
import java.util.Optional;

public interface Cache {
    Optional<String> get(String key);
    void put(String key, String value, Duration ttl);
    void delete(String key);
}
