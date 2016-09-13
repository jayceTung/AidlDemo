package com.asuper.aidldemo.glide;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Super on 2016/9/13.
 */
public class GlideMain {

    public static void main(String[] args) {
        Queue<String> queue = new ArrayDeque<String>();

        /**
         * result
         *
         * 5
         * -1
         * 1
         *
         */
        String str = "abcde fghl";
        System.out.println(str.indexOf(" "));
        System.out.println(str.indexOf("12"));
        System.out.println(str.indexOf("abcd"));

        System.out.println(str.substring(0, str.indexOf("de")));

        Pattern pattern = Pattern.compile("de");
        Matcher matcher = pattern.matcher(str);
        boolean isMe = false;
        if (matcher.find()) {
            isMe = true;
        }
        System.out.println(isMe);


        /**
         * match与find区别是 find是部分匹配 match是完全匹配
         */

    }
}
