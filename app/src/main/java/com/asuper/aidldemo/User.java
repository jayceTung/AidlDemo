package com.asuper.aidldemo;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by Super on 2016/9/8.
 */
public class User {
    private Queue<String> queue = new ArrayBlockingQueue<String>(100);
    private String name;
    private Integer order;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getOrder() {
        return order;
    }
    public void setOrder(Integer order) {
        this.order = order;
    }
}
