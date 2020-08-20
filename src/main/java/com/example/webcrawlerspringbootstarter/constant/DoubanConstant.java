package com.example.webcrawlerspringbootstarter.constant;

public class DoubanConstant {

    public final static String DOUBAN_DETAIL = "https://movie.douban.com/subject/27010768/?tag=%E7%83%AD%E9%97%A8&from=gaia";

    public final static String DOUBAN_MOVIE_NAME_XPATH = "//*[@id=\"content\"]/h1/span[1]";
    public final static String DOUBAN_MOVIE_TYPE_XPATH = "//*[@id=\"info\"]/span[5]";
    public final static String DOUBAN_MOVIE_SCORE_XPATH = "//*[@id=\"interest_sectl\"]/div[1]/div[2]/strong";
    public final static String DOUBAN_MOVIE_ENUMBER_XPATH = "//*[@id=\"interest_sectl\"]/div[1]/div[2]/div/div[2]/a/span";


    public final static String DOUBAN_MOVIE_EXPLORE = "https://movie.douban.com/j/search_subjects?type=movie&tag=%E5%8D%8E%E8%AF%AD&sort=recommend&page_limit=20&page_start=100";

}
