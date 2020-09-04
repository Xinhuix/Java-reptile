package com.example.webcrawlerspringbootstarter.service;

import com.example.webcrawlerspringbootstarter.entity.Movie;

/**
 * @ClassName MovieService
 * @Description
 * @Author xxh xinhui.xu@0071515.com
 * @Date 2020年09月04日 17:57
 **/
public interface MovieService {

    /**
     * 添加电影
     * @param movie
     */
    void insertMovie(Movie movie);
}
