package com.asuper.aidldemo.java;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by Super on 2016/9/8.
 */
public class Sort {

    public static void main(String[] args) {
        User user1 = new User();
        user1.setName("a");
        user1.setOrder(1);
        User user2 = new User();
        user2.setName("b");
        List<User> list = new ArrayList<User>();
        //此处add user2再add user1
        list.add(user2);
        list.add(user1);
//        Collections.sort(list, new Comparator<User>() {
//            @Override
//            public int compare(User lhs, User rhs) {
//                return lhs.getOrder().compareTo(rhs.getOrder());
//            }
//        });
        for (User u : list) {
            System.out.println(u.getName());
        }

        Map<User, String> map = new HashMap<User, String>();

        map = Collections.synchronizedMap(map);

        String str = UUID.randomUUID().toString().replace("-", "");
        System.out.println(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
        String str1 = "we are first";
        String string = str1.intern();
        System.out.println(1 & 0);
        int[] fun ={0,1,2,3,4,5,6};
        int[] ins = {7,8,9};
        System.arraycopy(ins,0,fun,3,3);
        for (int i : fun) {
            System.out.print(i);
        }

//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        try {
//            System.out.println(bufferedReader.readLine());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        String string1 = "123456789";
        StringBuffer stringBuffer = new StringBuffer(string1);
        System.out.println(stringBuffer.reverse().toString());

        float count = 12345678;
        DecimalFormat format = new DecimalFormat("#.##");
        double d = count / 10000.00;
        System.out.println(format.format(d));

        System.out.print(getString());
        int num = 11;
        double dd = num / count;
        if (dd > 0 && dd < 1) {
            System.out.print("true");
        }
        System.out.print(dd);
    }

    static String getString(){
        try{
            return "SUCCESS";
        }catch(Exception e){

        }finally{
            System.out.println("Finally is executing");
            return "ERROR";//如果这句放在finally之外呢？
        }

    }

    public static void getValue() {
        System.out.print("getValue");
    }

    public synchronized void set() {

    }
}
