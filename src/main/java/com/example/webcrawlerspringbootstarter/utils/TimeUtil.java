package com.example.webcrawlerspringbootstarter.utils;

import java.util.Random;

public class TimeUtil {

    public static void sleepRandom(int max){
        Random random = new Random();
        max = random.nextInt(max);
        try {
            Thread.sleep(max);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sleep(Long time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
