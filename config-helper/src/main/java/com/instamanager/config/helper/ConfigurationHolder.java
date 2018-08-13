package com.instamanager.config.helper;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class ConfigurationHolder {

    private static final Config config = loadConfig();

    private static Config loadConfig() {
        return ConfigFactory.load();
    }

    public static String env(String key) {
        return getConfig().getString(key);
    }

    public static Integer envInt(String key) {
        return getConfig().getInt(key);
    }

    private static Config getConfig() {
        return config;
    }
}
