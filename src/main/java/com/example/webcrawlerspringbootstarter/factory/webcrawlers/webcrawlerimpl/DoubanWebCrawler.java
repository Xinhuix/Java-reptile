package com.example.webcrawlerspringbootstarter.factory.webcrawlers.webcrawlerimpl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.webcrawlerspringbootstarter.constant.DoubanConstant;
import com.example.webcrawlerspringbootstarter.entity.Movie;
import com.example.webcrawlerspringbootstarter.entity.MovieData;
import com.example.webcrawlerspringbootstarter.entity.MovieType;
import com.example.webcrawlerspringbootstarter.factory.UrlQueue;
import com.example.webcrawlerspringbootstarter.factory.webcrawlers.WebCrawler;
import com.example.webcrawlerspringbootstarter.service.MovieService;
import com.example.webcrawlerspringbootstarter.service.MovieTypeService;
import com.example.webcrawlerspringbootstarter.utils.DateUtil;
import com.example.webcrawlerspringbootstarter.utils.MatchHtmlElementAttrValue;
import com.example.webcrawlerspringbootstarter.utils.TimeUtil;
import com.example.webcrawlerspringbootstarter.utils.WebCrawlerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.ListUtils;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author zhangnan
 */
@Service
@Slf4j
public class DoubanWebCrawler implements WebCrawler<MovieData> {

    @Autowired
    private MovieTypeService movieTypeService;
    @Autowired
    private MovieService movieService;

    private static final Integer flag = 1;

    private MovieData parseDetail(MovieData movieData) {
        TimeUtil.sleepRandom(1200);
        String detail = WebCrawlerUtil.downloadPageContext(movieData.getUrl());
        if (flag == 1) {
            getPiPiXiaMovie(detail, movieData);
        } else {
            String name = WebCrawlerUtil.parseOne(detail, DoubanConstant.DOUBAN_MOVIE_NAME_XPATH);
            String type = WebCrawlerUtil.parseOne(detail, DoubanConstant.DOUBAN_MOVIE_TYPE_XPATH);
            String eNumber = WebCrawlerUtil.parseOne(detail, DoubanConstant.DOUBAN_MOVIE_ENUMBER_XPATH);
            String score = WebCrawlerUtil.parseOne(detail, DoubanConstant.DOUBAN_MOVIE_SCORE_XPATH);
            movieData.setName(name);
            movieData.seteNumber(eNumber);
            movieData.setScore(score);
            movieData.setType(type);
        }
        movieData.setRate("123");
        movieData.setScore("99");
        movieData.setCover("www.baidu.com");
        movieData.setPlayable(true);
        movieData.seteNumber("123456");
        log.info("这是组装响应回去的数据：{}", movieData);
        return movieData;
    }


    private List<MovieData> getAll(String url) {
        System.out.println(url);
        String json = WebCrawlerUtil.downloadPageContext(url);
        System.out.println("转josn前：" + json);
        JSONObject jsonObject = JSONObject.parseObject(json);
        System.out.println("转josn后：" + jsonObject);
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
        if (flag == 1) {
            getFilmData(url, urlQueue);
        } else {
            setQueue(getAll(url), urlQueue);
        }

    }


    private void getFilmData(String str, UrlQueue urlQueue) {
        String json = WebCrawlerUtil.downloadPageContext(str);
        TimeUtil.sleepRandom(1200);
        //  System.out.println("这是豆瓣响应数据：" + detail);
        Set<String> match = MatchHtmlElementAttrValue.match(json, "a", "href");
        List<MovieData> list = new ArrayList<>();
        for (String m : match) {
            MovieData movieData = new MovieData();
            movieData.setName("");
            movieData.setUrl(m);
            movieData.setPlayable(true);
            movieData.setRate("");
            movieData.setCover("");
            list.add(movieData);
        }
        setQueue(list, urlQueue);
    }

    private void setQueue(List<MovieData> list, UrlQueue urlQueue) {
        for (MovieData movieData : list) {
            if (!StringUtils.isEmpty(movieData.getUrl())) {
                urlQueue.uPush(movieData);
                parseDetail(movieData);
                urlQueue.push(movieData);
            }
        }
    }

    private MovieData getPiPiXiaMovie(String detail, MovieData movieData) {
        List<String> list = WebCrawlerUtil.parseList(detail, DoubanConstant.MOVIE_WORKER);
        List<String> names = WebCrawlerUtil.parseList(detail, DoubanConstant.MOVIE_NAME);
        String[] split = names.get(0).split("]");
        String name = split[0].substring(1);
        String[] textStr = list.get(0).split("\\r\\n|\\n|\\r");
        Movie movie = new Movie();
        movie.setCreateTime(DateUtil.getDate());
        if (textStr.length != 0) {
            movie.setName(name);
            for (String data : textStr) {
                String content = data.substring(data.indexOf(":") + 1, data.length());
                String typeName = data.substring(0, data.indexOf(":"));
                if (data.contains(DoubanConstant.MOVIE_DIRECTOR)) {
                    movie.setDirector(content);
                    continue;
                }
                if (data.contains(DoubanConstant.MOVIE_SCREENWRITER)) {
                    movie.setScreenwriter(content);
                    continue;
                }
                if (data.contains(DoubanConstant.MOVIE_STARRING)) {
                    movie.setStarring(content);
                    continue;
                }
                if (data.contains(DoubanConstant.MOVIE_TYPE)) {
                    movieData.setName(name);
                    movieData.setType(data);
                    movie.setType(data);
                    //校验是否存在电影类型，不存在则插入
                    isExistMovieType(data);
                    continue;
                }
                if (data.contains(DoubanConstant.MOVIE_COUNTRY)) {
                    movie.setCountry(content);
                    continue;
                }
                if (data.contains(DoubanConstant.MOVIE_LANGUAGE)) {
                    movie.setLanguage(content);
                    continue;
                }
                if (DoubanConstant.movieReleaseTime.contains(typeName)) {
                    content = content.replaceAll("\\u00A0", "");
                    if (data.indexOf("(") != -1) {
                        movie.setReleaseTime(DateUtil.formatting(content.substring(0, content.indexOf("(")),
                                DateUtil.FORMATTING_DATE));
                    } else {
                        movie.setReleaseTime(DateUtil.formatting(content, DateUtil.FORMATTING_DATE));
                    }
                    continue;
                }
                if (data.contains(DoubanConstant.MOVIE_EPISODE)) {
                    movie.setEpisode(content);
                    continue;
                }
                if (DoubanConstant.movieDuration.contains(typeName)) {
                    movie.setDuration(content);
                    continue;
                }
                if (DoubanConstant.movieAlias.contains(typeName)) {
                    movie.setAlias(content);
                    continue;
                }
                if (data.contains(DoubanConstant.MOVIE_LINK)) {
                    movie.setLink(content);
                }
            }
        }
        //插入電影
        if (null != movie) {
            log.info("插入電影數據：{}", movie);
            movieService.insertMovie(movie);
        }

        return movieData;
    }

    /**
     * 校验是否存在电影类型，不存在则插入
     *
     * @param data
     */
    private void isExistMovieType(String data) {
        String[] movieType = data.substring(data.indexOf(":") + 1).split("/");
        //查询电影类型是否存在
        for (String typeName : movieType) {
            List<MovieType> movieTypes = movieTypeService.getMovieTypByKey("typeName", typeName);
            if (ListUtils.isEmpty(movieTypes)) {
                //查询最大type类型值
                MovieType movieMaxType = movieTypeService.selectMovieMaxType();
                //未找到该类型，则创建
                MovieType type = new MovieType();
                type.setType(movieMaxType.getType() + 1);
                type.setCreateTime(new Date());
                type.setTypeName(typeName);
                movieTypeService.insertMovieType(type);
            }
        }
    }
}
