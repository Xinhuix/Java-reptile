package com.example.webcrawlerspringbootstarter.entity;

/**
 * @author zhangnan
 */
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String geteNumber() {
        return eNumber;
    }

    public void seteNumber(String eNumber) {
        this.eNumber = eNumber;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isPlayable() {
        return playable;
    }

    public void setPlayable(boolean playable) {
        this.playable = playable;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
