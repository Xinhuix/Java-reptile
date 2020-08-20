package com.example.webcrawlerspringbootstarter.utils;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClientUtil {
    public static String get(String url) {
        CloseableHttpClient httpClient = HttpClients.custom()
                .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.100 Safari/537.36")
                .build();
        CloseableHttpResponse response = null;
        String responseStr = "";
        try {
            response = httpClient.execute(new HttpGet(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (response != null) {
                responseStr = EntityUtils.toString(response.getEntity());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseStr;
    }
}
