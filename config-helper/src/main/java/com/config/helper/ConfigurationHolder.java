package com.config.helper;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

class ConfigurationHolder {

    private static final Config config = loadConfig();

    private static Config loadConfig() {
        return ConfigFactory.load();
    }

    static Config getConfig() {
        return config;
    }
}
