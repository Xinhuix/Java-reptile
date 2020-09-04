package com.example.webcrawlerspringbootstarter.service.serviceimpl;

import com.example.webcrawlerspringbootstarter.dao.MovieTypeMapper;
import com.example.webcrawlerspringbootstarter.entity.MovieType;
import com.example.webcrawlerspringbootstarter.service.MovieTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName MovieTypeServiceImpl
 * @Description
 * @Author xxh xinhui.xu@0071515.com
 * @Date 2020年09月04日 14:25
 **/
@Service
@Slf4j
public class MovieTypeServiceImpl implements MovieTypeService {

    @Resource
    private MovieTypeMapper movieTypeMapper;

    @Override
    public MovieType selectMovieMaxType() {
        return movieTypeMapper.selectMovieMaxType();
    }

    @Override
    public List<MovieType> getMovieTypByKey(String key, Object value) {
        Example example = new Example(MovieType.class);
        example.createCriteria().andEqualTo(key, value);
        return movieTypeMapper.selectByExample(example);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertMovieType(MovieType movieType) {
        movieTypeMapper.insertSelective(movieType);
    }
}
