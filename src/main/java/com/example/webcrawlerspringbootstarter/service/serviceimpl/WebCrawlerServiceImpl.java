package com.example.webcrawlerspringbootstarter.service.serviceimpl;

import com.example.webcrawlerspringbootstarter.entity.MovieType;
import com.example.webcrawlerspringbootstarter.factory.UrlQueue;
import com.example.webcrawlerspringbootstarter.factory.WebCrawlerFactory;
import com.example.webcrawlerspringbootstarter.factory.webcrawlers.WebCrawler;
import com.example.webcrawlerspringbootstarter.service.MovieTypeService;
import com.example.webcrawlerspringbootstarter.service.WebCrawlerService;
import com.example.webcrawlerspringbootstarter.utils.BeanContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhangnan
 */
@Service
@Slf4j
public class WebCrawlerServiceImpl extends Thread implements WebCrawlerService {

    private String url;
    private UrlQueue urlQueue;
    private WebCrawler webCrawler;


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
        this.webCrawler = BeanContext.getApplicationContext().getBean(WebCrawler.class);

        /*WebCrawler webCrawler = WebCrawlerFactory.getWebCrawler(url);
        assert webCrawler != null;*/
        webCrawler.crawlData(url, urlQueue);
    }
}
