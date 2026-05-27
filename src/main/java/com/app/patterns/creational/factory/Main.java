package com.app.patterns.creational.factory;

import java.time.Duration;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {

        RedisClient redis = new RedisClientImpl("redis://localhost:6379");
        MemcachedClient memcached = new MemcachedClientImpl("memcached://localhost:11211");

        CacheFactory factory = new CacheFactory();
        factory.register(CacheBackend.REDIS,     prefix -> new RedisCache(redis, prefix));
        factory.register(CacheBackend.MEMCACHED, prefix -> new MemcachedCache(memcached, prefix));

        Cache userCache = factory.create(CacheBackend.REDIS, "user");
        userCache.put("123", "Uday Teja", Duration.ofMinutes(5));
        Optional<String> userHit = userCache.get("123");
        System.out.println("user:123 -> " + userHit);

        Cache sessionCache = factory.create(CacheBackend.MEMCACHED, "session");
        sessionCache.put("abc", "session-data", Duration.ofMinutes(30));
        sessionCache.delete("abc");

    }
}