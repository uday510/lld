package com.app.patterns.structural.decorator;

import com.app.patterns.creational.factory.Cache;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class MetricsCacheDecorator extends CacheDecorator {

    private final AtomicLong hits = new AtomicLong();
    private final AtomicLong misses = new AtomicLong();

    public MetricsCacheDecorator(Cache delegate) {
        super(delegate);
    }

    @Override
    public Optional<String> get(String key) {
        Optional<String> result = delegate.get(key);

        if (result.isPresent()) {
            hits.incrementAndGet();
        } else {
            misses.incrementAndGet();
        }

        return result;
    }

    public long getHits()    { return hits.get(); }
    public long getMisses()  { return misses.get(); }
    public double hitRate() {
        long total = hits.get() + misses.get();
        return total == 0 ? 0.0 : (double) hits.get() / total;
    }

}
