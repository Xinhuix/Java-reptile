package com.example.webcrawlerspringbootstarter.factory.webcrawlers.webcrawlerimpl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.webcrawlerspringbootstarter.constant.DoubanConstant;
import com.example.webcrawlerspringbootstarter.dao.MovieTypeMapper;
import com.example.webcrawlerspringbootstarter.entity.Movie;
import com.example.webcrawlerspringbootstarter.entity.MovieData;
import com.example.webcrawlerspringbootstarter.entity.MovieType;
import com.example.webcrawlerspringbootstarter.factory.UrlQueue;
import com.example.webcrawlerspringbootstarter.factory.webcrawlers.WebCrawler;
import com.example.webcrawlerspringbootstarter.utils.MatchHtmlElementAttrValue;
import com.example.webcrawlerspringbootstarter.utils.TimeUtil;
import com.example.webcrawlerspringbootstarter.utils.WebCrawlerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.thymeleaf.util.ListUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
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

    @Resource
    private MovieTypeMapper movieTypeMapper;

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
        System.out.println("这是电影名字：" + name);
        System.out.println("条数：" + list.size());
        String textStr[] = list.get(0).split("\\r\\n|\\n|\\r");
        Movie movie = new Movie();
        if (textStr.length != 0) {
            movie.setName(name);
            for (String data : textStr) {
                if (data.contains(DoubanConstant.MOVIE_DIRECTOR)) {
                    movie.setDirector(data);
                }
                if (data.contains(DoubanConstant.MOVIE_SCREENWRITER)) {
                    movie.setScreenwriter(data);
                }
                if (data.contains(DoubanConstant.MOVIE_STARRING)) {
                    movie.setStarring(data);
                }
                if (data.contains(DoubanConstant.MOVIE_TYPE)) {
                    movie.setType(data);
                    String[] movieType = data.substring(data.indexOf(":") + 1).split("/");
                    //查询电影类型是否存在
                    for (String typeName : movieType) {
                        Example example = new Example(MovieType.class);
                        example.createCriteria().andEqualTo("typeName", typeName);
                        List<MovieType> movieTypes = movieTypeMapper.selectByExample(example);
                        if (ListUtils.isEmpty(movieTypes)) {
                            //查询最大type类型值
                            MovieType movieMaxType = movieTypeMapper.selectMovieMaxType();
                            //未找到该类型，则创建
                            MovieType type = new MovieType();
                            type.setType(movieMaxType.getType() + 1);
                            type.setCreateTime(new Date());
                            type.setTypeName(typeName);
                            movieTypeMapper.insertSelective(type);
                        }
                    }
                }
            }
        }

        return movieData;
    }

    public static void main(String[] args) {
        String cc = "类型: 惊悚 / 恐怖";

    }
}
