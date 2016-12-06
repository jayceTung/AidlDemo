package com.asuper.aidldemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
        user2.setOrder(2);
        List<User> list = new ArrayList<User>();
        //此处add user2再add user1
        list.add(user2);
        list.add(user1);
        Collections.sort(list, new Comparator<User>() {
            @Override
            public int compare(User lhs, User rhs) {
                return lhs.getOrder().compareTo(rhs.getOrder());
            }
        });
        for(User u : list){
            System.out.println(u.getName());
        }

        Map<User, String> map = new HashMap<User, String>();

        map = Collections.synchronizedMap(map);

        String str = UUID.randomUUID().toString().replace("-", "");
        System.out.println(str);
    }

    public static void getValue() {
        System.out.print("getValue");
    }

    public synchronized void set(){

    }
}
