package com.example.webcrawlerspringbootstarter.factory;

import org.springframework.stereotype.Component;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author zhangnan
 */
@Component
public class UrlQueue {

    private ConcurrentLinkedDeque<Object> resultQueue = new ConcurrentLinkedDeque<>();
    private ConcurrentLinkedDeque<Object> urlQueue = new ConcurrentLinkedDeque<>();

    public void push(Object obj) {
        resultQueue.addLast(obj);
    }

    public Object poll() {
        return resultQueue.pollLast();
    }

    public boolean isEmpty() {
        return resultQueue.isEmpty();
    }

    public int size() {
        return resultQueue.size();
    }

    public void uPush(Object obj) {
        urlQueue.addLast(obj);
    }

    public Object uPoll() {
        return urlQueue.pollLast();
    }

    public boolean uIsEmpty() {
        return urlQueue.isEmpty();
    }

    public int uSize() {
        return urlQueue.size();
    }

    public void empty() {
        urlQueue.clear();
        resultQueue.clear();
    }
}
