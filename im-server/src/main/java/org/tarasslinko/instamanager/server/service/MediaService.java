package org.tarasslinko.instamanager.server.service;

import org.tarasslinko.instamanager.server.util.HttpHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static org.tarasslinko.instamanager.server.service.LoginService.BASE_URL;
import static org.tarasslinko.instamanager.server.util.HttpHelper.sendGet;

@Service
public class MediaService {

    private static final Logger logger = LoggerFactory.getLogger(MediaService.class);

    private LoginService loginService;

    public MediaService(LoginService loginService) {
        this.loginService = loginService;
    }

    public String getSelf() {
        logger.info("getSelf");
        return sendGet(BASE_URL + "v1/users/self/?access_token=" + loginService.getToken(), HttpHelper::getContent);
    }

    public String getSelfMedia() {
        logger.info("getSelfMedia");
        return sendGet(BASE_URL + "v1/users/self/media/recent/?access_token=" + loginService.getToken(), HttpHelper::getContent);
    }
}
