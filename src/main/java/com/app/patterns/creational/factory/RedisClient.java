package com.app.patterns.creational.factory;

public interface RedisClient {
    String get(String key);
    void setEx(String key, long seconds, String value);
    void del(String key);
}
