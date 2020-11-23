package com.asuper.aidldemo.java;

import java.util.concurrent.CyclicBarrier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author super
 * @date 2020/10/20
 */
public class Acm {
    public static void main(String[] args) {
        int[] nums = new int[0];
        CyclicBarrier barrier = new CyclicBarrier(10);
        String str = "/original.png";
        int i = str.lastIndexOf("/");
        System.out.println(checkNum("派送中。小哥今日体温正常，将佩戴口罩为您配送，也可联系小哥将包裹放置指定地点，祝您身体健康。,派送人：毛建军,电话:17337678901"));
//        System.out.println(checkNum("派送中。小哥今日体温正常，将佩戴口罩为您配送，也可联系小哥将包裹放置指定地点，祝您身体健康。,派送人：毛建军,电话:15990010346"));
    }
    public static String checkNum(String num){
        Pattern pattern = Pattern.compile("((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$*");
        Matcher matcher = pattern.matcher(num);
        StringBuffer bf = new StringBuffer();
        while (matcher.find()) {
            bf.append(matcher.group()).append(",");
        }
        int len = bf.length();
        if (len > 0) {
            bf.deleteCharAt(len - 1);
        }
        return bf.toString();
    }

}
