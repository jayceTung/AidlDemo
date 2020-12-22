package com.asuper.aidldemo.java;

import java.util.TreeMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantLock;
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
        System.out.println(checkNum("派送中13幢1单元小哥今日体温正常，将佩戴口罩为您配送，也可联系小哥将包裹放置指定地点，祝您身体健康。,派送人：毛建军,电话:"));
        System.out.println(barrier.hashCode());
//        System.out.println(checkNum("派送中。小哥今日体温正常，将佩戴口罩为您配送，也可联系小哥将包裹放置指定地点，祝您身体健康。,派送人：毛建军,电话:15990010346"));

        TreeMap<Integer, String> pairs = new TreeMap<>();
        pairs.put(2,  "B");

        pairs.put(1,  "A");

        pairs.put(3,  "C");
        pairs.ceilingEntry(3);

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

    class Solution {
        public boolean findNumberIn2DArray(int[][] matrix, int target) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return false;
            }
            int rows = matrix.length, columns = matrix[0].length;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    if (matrix[i][j] == target) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    class Node {
        private int prv;
        int next;
        Thread thread;


    }



    static class Entry {
        Entry left;
        Entry right;
        Entry parent;
    }
}
