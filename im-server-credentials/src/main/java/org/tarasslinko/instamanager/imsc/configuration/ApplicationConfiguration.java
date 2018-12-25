package org.tarasslinko.instamanager.imsc.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.tarasslinko.instamanager.config.helper.ConfigurationHolder.envInt;

@Configuration
public class ApplicationConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationConfiguration.class);

    @Bean
    public ServerProperties serverProperties() {
        ServerProperties properties = new ServerProperties();
        Integer port = envInt("server.port");
        logger.info("Server port: " + port);
        properties.setPort(port);
        return properties;
    }

}
