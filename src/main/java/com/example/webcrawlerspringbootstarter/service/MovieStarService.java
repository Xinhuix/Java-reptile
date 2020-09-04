package com.example.webcrawlerspringbootstarter.service;

import com.example.webcrawlerspringbootstarter.entity.MovieStar;

import java.util.List;

/**
 * @ClassName MovieStarService
 * @Description
 * @Author xxh xinhui.xu@0071515.com
 * @Date 2020年09月04日 18:41
 **/
public interface MovieStarService {

    /**
     * 插入电影
     * @param movieStar
     */
    void insertMovieStarList(List<MovieStar> movieStar);
}
