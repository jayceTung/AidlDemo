package com.asuper.aidldemo.thread;

/**
 * Created by Super on 2016/11/25.
 */

public class DeadLockTest implements Runnable{
    private int flag;
    private static Object o1 = new Object();
    private static Object o2 = new Object();

    @Override
    public void run() {
        if (flag == 1) {
            synchronized (o1) {
                try {
                    System.out.println("1-- sleep");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    System.out.println("1-- 02");
                }
            }

        }

        if (flag == 2) {
            synchronized (o2) {
                try {
                    System.out.println("2-- sleep");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    System.out.println("2-- 02");
                }
            }
        }

    }


    public static void main(String[] args) {
        DeadLockTest t1 = new DeadLockTest();
        DeadLockTest t2 = new DeadLockTest();
        t1.flag = 1;
        t2.flag = 2;

        new Thread(t1).start();
        new Thread(t2).start();
    }
}
