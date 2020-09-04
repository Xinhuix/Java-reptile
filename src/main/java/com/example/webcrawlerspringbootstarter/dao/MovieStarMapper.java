package com.example.webcrawlerspringbootstarter.dao;

import com.example.webcrawlerspringbootstarter.entity.MovieStar;
import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface MovieStarMapper extends BaseMapper<MovieStar>, MySqlMapper<MovieStar> {
}