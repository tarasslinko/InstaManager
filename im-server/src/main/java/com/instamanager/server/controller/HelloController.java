package com.instamanager.server.controller;

import com.instamanager.server.service.LoginService;
import com.instamanager.server.util.ConfigurationHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("login")
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    private LoginService loginService;

    public HelloController(LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping("")
    public void hello() {
        logger.info("Hello...");
        loginService.login();
    }

}
