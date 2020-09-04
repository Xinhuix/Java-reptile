package com.example.webcrawlerspringbootstarter.service;

import com.example.webcrawlerspringbootstarter.factory.UrlQueue;

/**
 * @author zhangnan
 */
public interface WebCrawlerService extends Runnable {
    /**
     * 爬虫开始抓取数据
     * @param url
     * @param urlQueue
     */
    void startCrawler(String url, UrlQueue urlQueue);
    /**
     * 爬虫停止抓取数据
     */
    void stopCrawler();


}
