package com.instamanager.server.controller;

import com.instamanager.server.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("login")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping("setBaseUrl")
    public void setUrl(@RequestParam String url) {
        loginService.setBaseUrl(url);
    }

    @RequestMapping(value = "getAuthUrl", produces = "text/plain")
    public String getAuthUrl() {
        logger.info("getAuthUrl");
        return loginService.getAuthUrl();
    }

    @RequestMapping("registerCode")
    public ModelAndView registerCode(@RequestParam String code, HttpServletResponse response) {
        loginService.setToken(code);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "getSelf", produces = "text/plain")
    public String getSelf() {
        logger.info("getSelf");
        return loginService.getSelf();
    }
    @RequestMapping(value = "getSelfMedia", produces = "text/plain")
    public String getSelfMedia() {
        logger.info("getSelfMedia");
        return loginService.getSelfMedia();
    }

}
