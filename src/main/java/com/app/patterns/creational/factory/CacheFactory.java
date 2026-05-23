package com.app.patterns.creational.factory;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;

public class CacheFactory {

   private final Map<CacheBackend, Function<String, Cache>> registry = new EnumMap<>(CacheBackend.class);

   public void register(CacheBackend backend, Function<String, Cache> builder) {
       registry.put(backend, builder);
   }

   public Cache create(CacheBackend backend, String keyPrefix) {
       Function<String, Cache> builder = registry.get(backend);
       if (builder == null) {
           throw new IllegalArgumentException("No cache backend registered: " + backend);
       }
       return builder.apply(keyPrefix);
   }

}
