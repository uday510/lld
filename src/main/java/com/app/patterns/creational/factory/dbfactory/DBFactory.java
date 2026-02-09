package com.app.patterns.creational.factory.dbfactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public class DBFactory {

    private static final Map<String, Supplier<DBConnection>> registry = new ConcurrentHashMap<>();

    private DBFactory() {

    }

    public static void register(
            String name,
            Supplier<DBConnection> supplier
    ) {

        if (name == null || supplier == null) {
            throw new IllegalArgumentException("Invalid registration");
        }

        registry.put(name.toLowerCase(), supplier);
    }

    public static DBConnection getConnection(String type) {

        if (type == null) {
            throw new IllegalArgumentException("DB type required");
        }

        Supplier<DBConnection> supplier = registry.get(type.toLowerCase());

        if (supplier == null) {
            throw new IllegalArgumentException(
                    "Unsupported DB: " + type
            );
        }

        return supplier.get();
    }
}
