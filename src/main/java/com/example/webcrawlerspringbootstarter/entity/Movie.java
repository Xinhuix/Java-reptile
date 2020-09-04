package com.example.webcrawlerspringbootstarter.entity;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "movie")
public class Movie {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 电影名字
     */
    private String name;

    /**
     * 导演
     */
    private String director;

    /**
     * 编剧
     */
    private String screenwriter;

    /**
     * 主演
     */
    private String starring;

    /**
     * 类型
     */
    private String type;

    /**
     * 制片国家
     */
    private String country;

    /**
     * 语言
     */
    private String language;

    /**
     * 上映时间
     */
    @Column(name = "release_time")
    private Date releaseTime;

    /**
     * 集数
     */
    private String episode;

    /**
     * 时长
     */
    private String duration;

    /**
     * 别名
     */
    private String alias;

    /**
     * IMDD链接
     */
    private String link;
}