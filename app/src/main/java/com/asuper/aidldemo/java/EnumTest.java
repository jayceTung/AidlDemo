package com.asuper.aidldemo.java;

/**
 * Created by super on 2017/8/1.
 */

public enum EnumTest {
    FILE_NOT_EXIST(100), FILE_EXIT(200);

    private int code;

    public int getCode() {
        return code;
    }

    EnumTest(int code) {
        this.code = code;
    }

    public static void main(String[] args) {
        for (EnumTest name : EnumTest.values()) {
            System.out.println(name.getCode());
        }
        System.out.println(EnumTest.FILE_EXIT.getDeclaringClass()
                + "" + EnumTest.FILE_NOT_EXIST.getDeclaringClass());
    }
}
