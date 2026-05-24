package com.app.patterns.structural.decorator;

import com.app.patterns.creational.factory.Cache;

import java.time.Duration;
import java.util.Optional;

public class LoggingCacheDecorator extends CacheDecorator {

    public LoggingCacheDecorator(Cache delegate) {
        super(delegate);
    }

    @Override
    public Optional<String> get(String key) {
        System.out.println("[log] cache.get key=" + key);
        Optional<String> result = delegate.get(key);
        System.out.println("[log] cache.get key=" + key + " hit=" + result.isPresent());
        return result;
    }

    @Override
    public void put(String key, String value, Duration ttl) {
        System.out.println("[log] cache.put key=" + key + " ttl=" + ttl.getSeconds() + "s");
        delegate.put(key, value, ttl);
    }

    @Override
    public void delete(String key) {
        System.out.println("[log] cache.delete key=" + key);
        delegate.delete(key);
    }

}
