package com.app.patterns.structural.flyweight;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BulletTypeFactory {

    private static final Map<String, BulletType> bulletTypeMap = new ConcurrentHashMap<>();

    private BulletTypeFactory() {}

    public static BulletType getBulletType(String color) {
        return bulletTypeMap.computeIfAbsent(color, k -> new BulletType(color));
    }

}
