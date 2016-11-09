package com.asuper.aidldemo.collection;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Super on 2016/11/9.
 */

public class MapDemo {
    public static void main(String[] args) {
        //synchron 
        final Map<String, Integer> map = new ConcurrentHashMap<String, Integer>();
        int i = 10;
        for (int i1 = 0; i1 < i; i1++) {
            map.put(String.valueOf(i1), i1);
        }


        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int j = 0; j < 10; j++) {
            final int index = j;
            try {
                Thread.sleep(index * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cachedThreadPool.execute(new Runnable() {
                public void run() {
                    int i = 10;
                    for (int i1 = 0; i1 < i; i1++) {
                        System.out.println(map.get(String.valueOf(i1)));
                    }
                }
            });
        }

    }
}
