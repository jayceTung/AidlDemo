package com.asuper.aidldemo.java;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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

        for (int i = 0; i < 10; i++) {
            System.out.println("isodd =" + isOdd(i));
        }
        float i = 10000;
        int j = 33;
        float k = (float) j;
        float percent = (1 - j / i) * 100;
        String mDefent = String.format("%.2f", percent);
        System.out.println("isodd  =" + mDefent);

        int a = 3 << 2;
        System.out.println("num = " + a);

        DateFormat df6 = DateFormat.getDateTimeInstance(DateFormat.SHORT,DateFormat.LONG, Locale.CHINA);
        //Date ------> Date对象
        //创建日期格式化对象   因为DateFormat类为抽象类 所以不能new
        DateFormat bf = new SimpleDateFormat("yyyy MMMMM d E a HH:mm:ss", Locale.US);//多态
        //2017-04-19 星期三 下午 20:17:38

        Date date = new Date();//创建时间
        String format = bf.format(System.currentTimeMillis());//格式化 bf.format(date);
        System.out.println(format);
    }

    public static boolean isOdd(int a) {
        if((a & 1) != 1){
            return true;
        }
        return false;
    }
}
