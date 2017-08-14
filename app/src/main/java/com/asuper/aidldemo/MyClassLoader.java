package com.asuper.aidldemo;

/**
 * Created by super on 2017/8/14.
 */

public class MyClassLoader extends ClassLoader {
    private static final String TAG = "MyClassLoader";

    public MyClassLoader() {
    }

    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {
        return super.findClass(className);
    }
}
