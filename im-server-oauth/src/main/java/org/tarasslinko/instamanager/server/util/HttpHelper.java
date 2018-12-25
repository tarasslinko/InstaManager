package org.tarasslinko.instamanager.server.util;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HttpHelper {

    private static final Logger logger = LoggerFactory.getLogger(HttpHelper.class);

    public static String getContent(InputStream inputStream) {
        try {
            return IOUtils.toString(inputStream, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String sendGet(String url, Function<InputStream, String> dataLoader) {
        HttpGet httpGet = new HttpGet(url);
        return sendRequest(httpGet, dataLoader);
    }

    public static  String sendPost(String url, Map<String, String> params, Function<InputStream, String> dataLoader) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(getEntity(params));
        return sendRequest(httpPost, dataLoader);
    }

    private static HttpEntity getEntity(Map<String, String> params) {
        List<NameValuePair> entityParams = params.entrySet().stream()
                .map(e -> new BasicNameValuePair(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
        return new UrlEncodedFormEntity(entityParams, Consts.UTF_8);
    }

    private static String sendRequest(HttpRequestBase request, Function<InputStream, String> dataLoader) {
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
