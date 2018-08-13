package com.config.helper;

public class ConfigHelper {

    public static String env(String key) {
        return ConfigurationHolder.getConfig().getString(key);
    }

    public static Integer envInt(String key) {
        return ConfigurationHolder.getConfig().getInt(key);
    }



}
