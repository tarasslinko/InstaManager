package com.instamanager.server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.config.helper.ConfigHelper.env;


@RestController
@RequestMapping("hello")
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping("")
    public  String hello() {
        logger.info("Hello...");
        return env("instagram.clientId");
    }

}
