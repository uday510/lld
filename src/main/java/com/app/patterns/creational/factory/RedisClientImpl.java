package com.app.patterns.creational.factory;

public class RedisClientImpl implements RedisClient {
    public RedisClientImpl(String url) { }

    @Override public String get(String key)                              { System.out.println("[redis] GET " + key); return null; }
    @Override public void setEx(String key, long seconds, String value)  { System.out.println("[redis] SETEX " + key + " " + seconds + " " + value); }
    @Override public void del(String key)                                { System.out.println("[redis] DEL " + key); }

}
