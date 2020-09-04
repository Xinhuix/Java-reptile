package com.example.webcrawlerspringbootstarter.service;

import com.example.webcrawlerspringbootstarter.entity.MovieType;

import java.util.List;

/**
 * @ClassName MovieTypeService
 * @Description
 * @Author xxh xinhui.xu@0071515.com
 * @Date 2020年09月03日 19:21
 **/
public interface MovieTypeService {
    /**
     * 获取电影类型最大值
     *
     * @return
     */
    MovieType selectMovieMaxType();

    /**
     * 通过指定的key获取值
     *
     * @param key
     * @param value
     * @return
     */
    List<MovieType> getMovieTypByKey(String key, Object value);

    /**
     * 插入电影类型
     * @param movieType
     */
    void insertMovieType(MovieType movieType);
}
