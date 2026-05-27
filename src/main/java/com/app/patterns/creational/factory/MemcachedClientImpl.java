package com.app.patterns.creational.factory;

public class MemcachedClientImpl implements MemcachedClient {
    public MemcachedClientImpl(String url) { /* connect */ }

    @Override public Object get(String key)                            { System.out.println("[memcached] GET " + key); return null; }
    @Override public void set(String key, int ttl, String value)       { System.out.println("[memcached] SET " + key + " " + ttl + " " + value); }
    @Override public void delete(String key)                           { System.out.println("[memcached] DELETE " + key); }

}
