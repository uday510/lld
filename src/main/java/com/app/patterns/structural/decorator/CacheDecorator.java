package com.app.patterns.structural.decorator;

import com.app.patterns.creational.factory.Cache;

import java.time.Duration;
import java.util.Optional;

public abstract class CacheDecorator implements Cache {

    protected final Cache delegate;

    protected CacheDecorator(Cache delegate) {
        this.delegate = delegate;
    }

    @Override
    public Optional<String> get(String key) {
        return delegate.get(key);
    }

    @Override
    public void put(String key, String value, Duration ttl) {
        delegate.put(key, value, ttl);
    }

    @Override
    public void delete(String key) {
        delegate.delete(key);
    }
}
