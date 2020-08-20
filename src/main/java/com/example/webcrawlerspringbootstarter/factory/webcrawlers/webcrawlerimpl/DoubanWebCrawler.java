package com.example.webcrawlerspringbootstarter.factory.webcrawlers.webcrawlerimpl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.webcrawlerspringbootstarter.constant.DoubanConstant;
import com.example.webcrawlerspringbootstarter.entity.MovieData;
import com.example.webcrawlerspringbootstarter.factory.UrlQueue;
import com.example.webcrawlerspringbootstarter.factory.webcrawlers.WebCrawler;
import com.example.webcrawlerspringbootstarter.utils.TimeUtil;
import com.example.webcrawlerspringbootstarter.utils.WebCrawlerUtil;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangnan
 */
public class DoubanWebCrawler implements WebCrawler<MovieData> {

    private MovieData parseDetail(MovieData movieData) {
        TimeUtil.sleepRandom(1200);
        String detail = WebCrawlerUtil.downloadPageContext(movieData.getUrl());
        System.out.println("这是豆瓣响应数据：" + detail);
        String name = WebCrawlerUtil.parseOne(detail, DoubanConstant.DOUBAN_MOVIE_NAME_XPATH);
        String type = WebCrawlerUtil.parseOne(detail, DoubanConstant.DOUBAN_MOVIE_TYPE_XPATH);
        String eNumber = WebCrawlerUtil.parseOne(detail, DoubanConstant.DOUBAN_MOVIE_ENUMBER_XPATH);
        String score = WebCrawlerUtil.parseOne(detail, DoubanConstant.DOUBAN_MOVIE_SCORE_XPATH);
        movieData.setName(name);
        movieData.setENumber(eNumber);
        movieData.setScore(score);
        movieData.setType(type);
        return movieData;
    }


    private List<MovieData> getAll(String url) {
        String json = WebCrawlerUtil.downloadPageContext(url);
        System.out.println(json);
        JSONObject jsonObject = JSONObject.parseObject(json);
        JSONArray jsonArray = (JSONArray) jsonObject.get("subjects");
        List<MovieData> list = new ArrayList<>();
        for (Object obj : jsonArray) {
            MovieData movieData = new MovieData();
            JSONObject next = (JSONObject) obj;
            movieData.setName(next.getString("title"));
            movieData.setUrl(next.getString("url"));
            movieData.setPlayable(next.getBoolean("playable"));
            movieData.setRate(next.getString("rate"));
            movieData.setCover(next.getString("cover"));
            list.add(movieData);
        }
        return list;
    }

    @Override
    public void crawlData(String url, UrlQueue urlQueue) {
        List<MovieData> all = getAll(url);
        for (MovieData movieData : all) {
            if (!StringUtils.isEmpty(movieData.getUrl())) {
                urlQueue.uPush(movieData);
                parseDetail(movieData);
                urlQueue.push(movieData);
            }
        }
    }
}
