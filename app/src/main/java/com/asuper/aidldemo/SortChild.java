package com.asuper.aidldemo;

/**
 * Created by Super on 2016/9/12.
 */
public class SortChild extends Sort {

    public void get() {
        SortChild.getValue();
    }

    @Override
    public synchronized void set() {
        super.set();
    }
}
