package com.example.webcrawlerspringbootstarter.entity;

import lombok.Data;

/**
 * @author zhangnan
 */
@Data
public class MovieData {
    private String name;
    private String type;
    private String score;
    private String eNumber;

    private String rate;
    private String url;
    private boolean playable;
    private String cover;

    @Override
    public String toString() {
        return "" +
                "--电影名称: " + name + "\n" +
                "----类型:  " + type + "\n" +
                "----评分:  " + score + "\n" +
                "----评论数:  " + eNumber + "\n" +
                "----上否上映:  " + playable +"\n"+
                "----海报地址:  " + cover + "\n";
    }
}
