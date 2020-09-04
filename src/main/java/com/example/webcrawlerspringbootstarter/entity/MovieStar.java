package com.example.webcrawlerspringbootstarter.entity;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "movie_star")
public class MovieStar {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 名字
     */
    private String name;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 国家
     */
    private String country;

    /**
     * 明星照片
     */
    @Column(name = "star_photo")
    private String starPhoto;
}