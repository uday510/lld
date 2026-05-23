package com.app.patterns.creational.factory;

public interface MemcachedClient {
    Object get(String key);
    void set(String key, int ttlSeconds, String value);
    void delete(String key);
}
