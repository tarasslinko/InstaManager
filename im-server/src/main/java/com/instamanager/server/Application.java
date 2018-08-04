package com.instamanager.server;

import com.instamanager.server.util.WarnUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        WarnUtil.disableWarning();
        SpringApplication.run(Application.class, args);
    }

}
