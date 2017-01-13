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
        public String address;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public Info getInfo() {
            return info;
        }

        public void setInfo(Info info) {
            this.info = info;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", info=" + info +
                    ", address='" + address + '\'' +
                    '}';
        }
    }

    public static class Info{
        public Info() {}
        String msg;
    }

    private static void formatCount(String count) {
        double num = Double.valueOf(count);
        if (num >= 10000) {
            num /= 10000;
            System.out.println("format = " + String.format("%.1f", num));
        } else {
            System.out.println("format = " + count);
        }
    }

    public static void main(String[] args) {
        String gsonValue = "{name:'非子墨',age:23,info:{msg:'I am a student!'}}";
        Gson gson = new Gson();
        User user = gson.fromJson(gsonValue, User.class);
        String json = gson.toJson(user);
        System.out.println("user = "+user.toString());
        System.out.println("json = " + json);

//        int l = 10111;
//        l /= 1;
//        String.format("0.1%f", 1);
//        System.out.println("format = " + l);
        String d = "31415.926";
        formatCount(d);
        String mPhoneNum = "15990010346";
        String num = mPhoneNum.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
        System.out.print("mPhoneNum" + mPhoneNum + "num" + num);

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
