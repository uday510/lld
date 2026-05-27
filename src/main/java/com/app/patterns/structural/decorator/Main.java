package com.app.patterns.structural.decorator;

import com.app.patterns.creational.factory.Cache;
import com.app.patterns.creational.factory.RedisCache;
import com.app.patterns.creational.factory.RedisClient;
import com.app.patterns.creational.factory.RedisClientImpl;

import java.time.Duration;
import java.util.Optional;

public class Main {


    static void main() {

        RedisClient redis = new RedisClientImpl("redis://localhost:6379");
        Cache base = new RedisCache(redis, "user");

        MetricsCacheDecorator withMetrics = new MetricsCacheDecorator(base);

        Cache cache = new LoggingCacheDecorator(withMetrics);

        cache.put("123", "Uday Teja", Duration.ofMinutes(5));
        Optional<String> r1 = cache.get("123");
        Optional<String> r2 = cache.get("456");
        Optional<String> r3 = cache.get("789");

        System.out.println();
        System.out.println("Hits:     " + withMetrics.getHits());
        System.out.println("Misses:   " + withMetrics.getMisses());
        System.out.println("Hit rate: " + withMetrics.hitRate());
    }
}
