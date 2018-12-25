package org.tarasslinko.instamanager.server.util;


import org.tarasslinko.instamanager.config.helper.ConfigurationHolder;

public class ConfigurationHelper {

    public static String getClientId() {
        return ConfigurationHolder.env("instagram.clientId");
    }

    public static String getClientSecret() {
        return ConfigurationHolder.env("instagram.clientSecret");
    }

}
