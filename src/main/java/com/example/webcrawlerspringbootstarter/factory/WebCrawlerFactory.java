package com.example.webcrawlerspringbootstarter.factory;

import com.example.webcrawlerspringbootstarter.factory.webcrawlers.WebCrawler;
import com.example.webcrawlerspringbootstarter.factory.webcrawlers.webcrawlerimpl.DoubanWebCrawler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangnan
 */
public class WebCrawlerFactory {

    private static Map<String, WebCrawler> webCrawlers = new HashMap<>();
    private final static String URL_DOUBAN = "https://www.douban.com/";

    static {
        webCrawlers.put(WebCrawlerFactory.URL_DOUBAN, new DoubanWebCrawler());
    }

    public static WebCrawler getWebCrawler(String url) {
        WebCrawler webCrawler = null;
        if(url.contains(WebCrawlerFactory.URL_DOUBAN)){
            webCrawler = webCrawlers.get(WebCrawlerFactory.URL_DOUBAN);
        }
        return webCrawler == null ? new DoubanWebCrawler() : webCrawler;

    }
}
