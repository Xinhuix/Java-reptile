package com.example.webcrawlerspringbootstarter.dao;

import com.example.webcrawlerspringbootstarter.entity.MovieType;
import tk.mybatis.mapper.common.Mapper;

public interface MovieTypeMapper extends Mapper<MovieType> {

    MovieType selectMovieMaxType();
}