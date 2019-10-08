package org.tarasslinko.instamanager.server.service;

import org.tarasslinko.instamanager.server.util.FileUtil;
import org.tarasslinko.instamanager.server.util.HttpHelper;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.tarasslinko.instamanager.server.util.ConfigurationHelper.getClientId;
import static org.tarasslinko.instamanager.server.util.ConfigurationHelper.getClientSecret;
import static org.tarasslinko.instamanager.server.util.HttpHelper.sendGet;
import static org.tarasslinko.instamanager.server.util.HttpHelper.sendPost;

@Service
public class LoginService {

    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

    public static final String BASE_URL = "https://api.instagram.com/";

    private static final String TOKEN_FILE_NAME = ".token";

    private String baseUrl;
    private String access_token;

    public LoginService() {
        Optional<String> read = FileUtil.read(Path.of(TOKEN_FILE_NAME));
        if (read.isPresent()) {
            access_token = read.get();
            logger.info("Token is present: {}", access_token);
        }
    }

    public void setBaseUrl(String baseUrl) {
        if (this.baseUrl == null) {
            logger.info("Set base url:" + baseUrl);
            this.baseUrl = baseUrl;
        }
    }

    public boolean logined() {
        return access_token != null;
    }

    public String getToken() {
        return access_token;
    }

    public void setToken(String code) {
        logger.info("Set token: " + code);
        Map<String, String> params = new HashMap<>();
        params.put("client_id", getClientId());
        params.put("client_secret", getClientSecret());
        params.put("grant_type", "authorization_code");
        params.put("redirect_uri", baseUrl + "login/registerCode");
        params.put("code", code);

        String response = sendPost(BASE_URL + "oauth/access_token", params, HttpHelper::getContent);
        logger.info("___________________________________________________________");
        logger.info(response);
        JSONObject resp = new JSONObject(response);
        this.access_token = resp.getString("access_token");
        FileUtil.write(access_token, Path.of(TOKEN_FILE_NAME));
    }

    public String getAuthUrl() {
        return BASE_URL + "oauth/authorize/?client_id="
                + getClientId()
                + "&scope=basic"
                //+ "&scope=basic+follower_list"
                + "&response_type=code&redirect_uri="
                + baseUrl + "login/registerCode";
    }


}
