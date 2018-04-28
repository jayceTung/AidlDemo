package com.asuper.aidldemo.java;

import java.io.FileNotFoundException;

/**
 * @author super
 * @date 2018/4/27
 */
public class Tiger implements Animal {
    @Override
    public void eat() {
        System.out.print("\nTiger eat");
    }

    @Override
    public void say() throws FileNotFoundException {
        System.out.print("\nTiger say");
    }
}
