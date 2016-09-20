package com.asuper.aidldemo;

import com.google.gson.Gson;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Super on 2016/9/12.
 */
public class SortChild extends Sort {

    public void get() {
        SortChild.getValue();
    }

    @Override
    public synchronized void set() {
        super.set();
    }

    public static class User{
        public String name;
        public int age;
        public Info info;
        @Override
        public String toString() {
            // TODO Auto-generated method stub
            return "["+name+":"+info.msg+"]";
        }
    }

    public static class Info{
        public Info() {}
        String msg;
    }
    public static void main(String[] args) {
        String gsonValue = "{name:'非子墨',age:23,info:{msg:'I am a student!'}}";
        Gson gson = new Gson() ;
        User user = gson.fromJson(gsonValue, User.class);
        String json = gson.toJson(user);
        System.out.println("user = "+user);
        System.out.println("json = " + json);

        try {
            Constructor<?> con = Sort.class.getDeclaredConstructor();
            Sort sort = (Sort) con.newInstance();
            Field[] fileds = User.class.getDeclaredFields();
            for (Field file : fileds) {
                file.setAccessible(true);
                System.out.println(file.getName());
            }

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
