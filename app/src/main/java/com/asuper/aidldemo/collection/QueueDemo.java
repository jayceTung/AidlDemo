package com.asuper.aidldemo.collection;


import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Super on 2016/11/3.
 */

public class QueueDemo {
    private static Queue queue = new ArrayBlockingQueue(100);
    public static void main(String[] args) {
        /* 取出队列头 */
        System.out.println(queue.peek());

        int count = 10;
        for (int i = 0; i < count; i++) {
            ReentrantLock lock = new ReentrantLock(false);
            lock.lock();
            queue.offer(i);
            lock.unlock();
        }

        for (int i = 0; i < count; i++) {
            System.out.println(queue.poll());
        }
    }
}
