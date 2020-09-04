package com.example.webcrawlerspringbootstarter.service.serviceimpl;

import com.example.webcrawlerspringbootstarter.dao.MovieStarMapper;
import com.example.webcrawlerspringbootstarter.entity.MovieStar;
import com.example.webcrawlerspringbootstarter.service.MovieStarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName MoiveStarImpl
 * @Description
 * @Author xxh xinhui.xu@0071515.com
 * @Date 2020年09月04日 18:46
 **/
@Service
@Slf4j
public class MovieStarImpl implements MovieStarService {

    @Resource
    private MovieStarMapper movieStarMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertMovieStarList(List<MovieStar> movieStar) {
        movieStarMapper.insertList(movieStar);
    }
}
