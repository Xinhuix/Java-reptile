package com.example.webcrawlerspringbootstarter;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.example.webcrawlerspringbootstarter.dao")
public class WebcrawlerSpringBootStarterApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebcrawlerSpringBootStarterApplication.class, args);
    }

}
