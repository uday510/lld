package com.app.patterns.creational.singleton;

import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    private Properties properties;

    private ConfigManager() {
        loadConfig();
    }

    private static class Holder {
        private static final ConfigManager INSTANCE = new ConfigManager();
    }

    public static ConfigManager getInstance() {
        return Holder.INSTANCE;
    }


    private void loadConfig() {

        try (InputStream input =
                getClass().getClassLoader()
                        .getResourceAsStream("config.properties")) {

            properties = new Properties();

            if (input == null) {
                throw new RuntimeException("config.properties not found");
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to load config");
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

}
