package com.example.webcrawlerspringbootstarter.entity;

import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "movie_resource_real")
public class MovieResourceReal {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 电影主键
     */
    private Long movie;

    /**
     * 电影资源路径
     */
    @Column(name = "resource_url")
    private String resourceUrl;

    /**
     * 提取密码
     */
    private String password;

    /**
     * 状态
     */
    private String status;
}