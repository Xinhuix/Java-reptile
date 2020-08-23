package com.example.webcrawlerspringbootstarter.entity;

import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "movie_type_real")
public class MovieTypeReal {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 电影主键
     */
    @Column(name = "movie_id")
    private Long movieId;

    /**
     * 电影类型表主键
     */
    @Column(name = "movie_type_id")
    private Long movieTypeId;
}