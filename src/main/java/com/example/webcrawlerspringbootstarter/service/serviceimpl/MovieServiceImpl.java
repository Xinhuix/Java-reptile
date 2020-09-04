package com.example.webcrawlerspringbootstarter.service.serviceimpl;

import com.example.webcrawlerspringbootstarter.dao.MovieMapper;
import com.example.webcrawlerspringbootstarter.entity.Movie;
import com.example.webcrawlerspringbootstarter.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @ClassName MovieServiceImpl
 * @Description
 * @Author xxh xinhui.xu@0071515.com
 * @Date 2020年09月04日 17:58
 **/
@Slf4j
@Service
public class MovieServiceImpl implements MovieService {

    @Resource
    private MovieMapper movieMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertMovie(Movie movie) {
        movieMapper.insertSelective(movie);
    }
}
