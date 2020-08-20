package com.example.webcrawlerspringbootstarter.service.serviceimpl;

import com.example.webcrawlerspringbootstarter.factory.UrlQueue;
import com.example.webcrawlerspringbootstarter.factory.WebCrawlerFactory;
import com.example.webcrawlerspringbootstarter.factory.webcrawlers.WebCrawler;
import com.example.webcrawlerspringbootstarter.service.WebCrawlerService;

/**
 * @author zhangnan
 */
public class WebCrawlerServiceImpl extends Thread implements WebCrawlerService {

    private String url;
    private UrlQueue urlQueue;

    @Override
    public void startCrawler(String url, UrlQueue urlQueue) {
        this.url = url;
        this.urlQueue = urlQueue;
        this.start();
    }

    @Override
    public void stopCrawler() {
        this.interrupt();
    }

    @Override
    public void run() {
        WebCrawler webCrawler = WebCrawlerFactory.getWebCrawler(url);
        assert webCrawler != null;
        webCrawler.crawlData(url,urlQueue);
    }
}
