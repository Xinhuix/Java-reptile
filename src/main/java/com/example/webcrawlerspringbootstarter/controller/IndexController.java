package com.example.webcrawlerspringbootstarter.controller;

import com.alibaba.fastjson.JSON;
import com.example.webcrawlerspringbootstarter.constant.DoubanConstant;
import com.example.webcrawlerspringbootstarter.entity.MovieData;
import com.example.webcrawlerspringbootstarter.factory.UrlQueue;
import com.example.webcrawlerspringbootstarter.service.WebCrawlerService;
import com.example.webcrawlerspringbootstarter.service.serviceimpl.WebCrawlerServiceImpl;
import com.example.webcrawlerspringbootstarter.utils.TimeUtil;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author zhangnan
 */
@Controller
public class IndexController {

    @Autowired
    private UrlQueue urlQueue;

    private List<WebCrawlerService> webCrawlerServices = new ArrayList<>();

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private WebCrawlerService webCrawlerService;

    @Value("${crawler.type}")
    private String type;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @PostMapping("/data")
    @ResponseBody
    public void data(String url) {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("crawler-runner-%d").build();
        ExecutorService service = new ThreadPoolExecutor(10,
                20,200L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(),
                namedThreadFactory);

        url = StringUtils.isEmpty(url) ? DoubanConstant.DOUBAN_MOVIE_EXPLORE : url;
        WebCrawlerService webCrawlerService = new WebCrawlerServiceImpl();
        webCrawlerService.startCrawler(url,urlQueue);
        webCrawlerServices.add(webCrawlerService);

        service.submit(new CrawlerResTask(urlQueue));
        service.submit(new CrawlerUrlTask(urlQueue));
    }

    @RequestMapping("/stop")
    @ResponseBody
    public void stop() {
        urlQueue.empty();
        for (WebCrawlerService webCrawlerService : webCrawlerServices) {
            webCrawlerService.stopCrawler();
        }
    }


    public static class CrawlerUrlTask implements Runnable {

        private UrlQueue urlQueue;
        CrawlerUrlTask(UrlQueue urlQueue) {
            this.urlQueue = urlQueue;
        }

        @Override
        public void run() {
            System.out.println("name----"+Thread.currentThread().getName());
            Long begin = System.currentTimeMillis();
            Long end ;
            while (true) {
                if(urlQueue.uIsEmpty()){
                    TimeUtil.sleep(200L);
                    end = System.currentTimeMillis();
                    if (end - begin > 30000) {
                        break;
                    }
                    continue;
                }
                begin = System.currentTimeMillis();
                MovieData poll = (MovieData) urlQueue.uPoll();
                WebSocketServer.sendInfo(JSON.toJSONString(poll), "user");
                TimeUtil.sleep(100L);
            }
        }
    }

    public static class CrawlerResTask implements Runnable {

        private UrlQueue urlQueue;
        CrawlerResTask(UrlQueue urlQueue) {
            this.urlQueue = urlQueue;
        }

        @Override
        public void run() {
            Long begin = System.currentTimeMillis();
            Long end;
            while (true) {
                if(urlQueue.isEmpty()){
                    TimeUtil.sleep(100L);
                    end = System.currentTimeMillis();
                    if (end - begin > 30000) {
                        break;
                    }
                    continue;
                }
                begin = System.currentTimeMillis();
                MovieData poll = (MovieData) urlQueue.poll();
                logger.warn("解析的电影是：{}{}{}",poll,1,2);
                WebSocketServer.sendInfo(JSON.toJSONString(poll), "user");
            }
        }
    }
}

