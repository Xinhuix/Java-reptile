package com.example.webcrawlerspringbootstarter.constant;

import java.util.ArrayList;
import java.util.List;

public class DoubanConstant {

    public final static String DOUBAN_DETAIL = "https://movie.douban.com/subject/27010768/?tag=%E7%83%AD%E9%97%A8&from=gaia";

    public final static String DOUBAN_MOVIE_NAME_XPATH = "//*[@id=\"content\"]/h1/span[1]";
    public final static String DOUBAN_MOVIE_TYPE_XPATH = "//*[@id=\"info\"]/span[5]";
    public final static String DOUBAN_MOVIE_SCORE_XPATH = "//*[@id=\"interest_sectl\"]/div[1]/div[2]/strong";
    public final static String DOUBAN_MOVIE_ENUMBER_XPATH = "//*[@id=\"interest_sectl\"]/div[1]/div[2]/div/div[2]/a/span";


    public final static String DOUBAN_MOVIE_EXPLORE = "https://movie.douban.com/j/search_subjects?type=movie&tag=%E5%8D%8E%E8%AF%AD&sort=recommend&page_limit=20&page_start=100";

    public final static String MOVIE_WORKER = "//*[@class=\"entry-content u-clearfix\"]/p";

    public final static String DOUBAN_MOVIE_URL_XPATH = "//*[@class=\"entry-title\"]";

    public final static String MOVIE_URL = "//*[@class=\"entry-title\"]/href=\"([^\"]*?)\"/i";

    public final static String MOVIE_NAME = "//*[@class=\"entry-title\"]";

    public final static String MOVIE_DIRECTOR = "导演";

    public final static String MOVIE_STARRING = "主演";

    public final static String MOVIE_SCREENWRITER = "编剧";

    public final static String MOVIE_TYPE = "类型";

    public final static String MOVIE_COUNTRY = "制片国家/地区";

    public final static String MOVIE_LANGUAGE = "语言";

    public final static String MOVIE_RELEASE_TIME = "上映时间";

    public final static String MOVIE_EPISODE = "集数";

    public static List<String> movieDuration = new ArrayList<>();
    public static List<String> movieAlias = new ArrayList<>();
    public static List<String> movieReleaseTime = new ArrayList<>();


    static {
        movieDuration.add("时长");
        movieDuration.add("片长");
        movieAlias.add("别名");
        movieAlias.add("又名");
        movieReleaseTime.add("上映时间");
        movieReleaseTime.add("上映日期");
        movieReleaseTime.add("首播");

    }

    public final static String MOVIE_DURATION = "时长片长";

    public final static String MOVIE_ALIAS = "";

    public final static String MOVIE_LINK = "IMDb链接";

}
