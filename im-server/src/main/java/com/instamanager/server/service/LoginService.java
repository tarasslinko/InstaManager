package com.instamanager.server.service;

import com.instamanager.server.util.ConfigurationHelper;
import org.apache.commons.io.IOUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.instamanager.server.util.ConfigurationHelper.getClientId;
import static com.instamanager.server.util.ConfigurationHelper.getClientSecret;

@Service
public class LoginService {

    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

    private static final String BASE_URL = "https://api.instagram.com/";

    private String baseUrl;
    private String access_token;

    public void setBaseUrl(String baseUrl) {
        logger.info("Set base url:" + baseUrl);
        this.baseUrl = baseUrl;
    }

    public void setToken(String code) {
        logger.info("Set token: " + code);
        Map<String,String> params = new HashMap<>();
        params.put("client_id", getClientId());
        params.put("client_secret", getClientSecret());
        params.put("grant_type", "authorization_code");
        params.put("redirect_uri", baseUrl + "login/registerCode");
        params.put("code", code);

        String response = sendPost(BASE_URL + "oauth/access_token", params, this::getContent);
        logger.info("___________________________________________________________");
        logger.info(response);
        JSONObject resp = new JSONObject(response);
        this.access_token = resp.getString("access_token");
    }

    public String getAuthUrl() {
        return BASE_URL + "oauth/authorize/?client_id="
                + getClientId()
                + "&scope=basic+likes+comments"
                + "&response_type=code&redirect_uri="
                + baseUrl + "login/registerCode";
    }

    public String getSelf() {
        return sendGet(BASE_URL + "v1/users/self/?access_token=" + access_token, this::getContent);
    }

    public String getSelfMedia() {
        return sendGet(BASE_URL + "v1/users/self/media/recent/?access_token=" + access_token, this::getContent);
    }

    private String getContent(InputStream inputStream) {
        try {
            return IOUtils.toString(inputStream, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String sendGet(String url, Function<InputStream, String> dataLoader) {
        HttpGet httpGet = new HttpGet(url);
        return sendRequest(httpGet, dataLoader);
    }

    private String sendPost(String url, Map<String,String> params, Function<InputStream, String> dataLoader) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(getEntity(params));
        return sendRequest(httpPost, dataLoader);
    }

    private HttpEntity getEntity(Map<String, String> params) {
        List<NameValuePair> entityParams = params.entrySet().stream()
                .map(e -> new BasicNameValuePair(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
        return new UrlEncodedFormEntity(entityParams, Consts.UTF_8);
    }

    private String sendRequest(HttpRequestBase request, Function<InputStream, String> dataLoader) {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        String result = null;
        try {
            HttpResponse response = client.execute(request);
            result = dataLoader.apply(response.getEntity().getContent());
        } catch (Exception e) {
            logger.error("Error on request to url: " + request.getURI().toString(), e);
        }
        return result;
    }



}
