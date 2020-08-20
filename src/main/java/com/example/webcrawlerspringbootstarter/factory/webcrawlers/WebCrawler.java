package com.example.webcrawlerspringbootstarter.factory.webcrawlers;

import com.example.webcrawlerspringbootstarter.factory.UrlQueue;

/**
 * @author zhangnan
 */
public interface WebCrawler<T> {
    /**
     * 抓取数据统一接口
     * @param url
     * @param urlQueue
     */
    void crawlData(String url, UrlQueue urlQueue);
}
