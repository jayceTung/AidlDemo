package com.asuper.aidldemo.java;

import java.io.FileNotFoundException;

/**
 * @author super
 * @date 2018/4/27
 */
public interface Animal {
    void eat();

    void say() throws FileNotFoundException;
}
