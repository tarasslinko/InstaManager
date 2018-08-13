package com.instamanager.server.service;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    public void login() {
//        CloseableHttpClient client = HttpClientBuilder.create().build();

        HttpGet login = new HttpGet();
        //login.setParams();
    }

//    private URIBuilder getUri() {
//        URIBuilder uriBuilder = new URIBuilder("https://api.instagram.com/oauth/authorize/")
//                .addParameter("client_id", ConfigurationHelper.getClientId())
//                .addParameter("redirect_uri", )
//                ;
//    }

}
