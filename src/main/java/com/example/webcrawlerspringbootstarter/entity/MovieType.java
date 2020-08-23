package com.example.webcrawlerspringbootstarter.entity;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "movie_type")
public class MovieType {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 电影类型
     */
    private Long type;

    /**
     * 电影类型名字
     */
    @Column(name = "type_name")
    private String typeName;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;
}