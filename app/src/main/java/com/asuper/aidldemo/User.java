package com.asuper.aidldemo;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by Super on 2016/9/8.
 */
public class User {
    private Queue<String> queue = new ArrayBlockingQueue<String>(100);
    private final BaiduMap map = new BaiduMap();

    public BaiduMap getMap() {
        return map;
    }

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

    public static class BaiduMap {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
