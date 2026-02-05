package com.app.patterns.creational.singleton;

public class ConfigManagerWithVolatile {

    private static volatile ConfigManagerWithVolatile instance;

    private ConfigManagerWithVolatile() {}

    public static ConfigManagerWithVolatile getInstance() {

        if (instance == null) {

            synchronized (ConfigManagerWithVolatile.class) {

                if (instance == null) {
                    instance = new ConfigManagerWithVolatile();
                }
            }
        }

        return instance;
    }

}
