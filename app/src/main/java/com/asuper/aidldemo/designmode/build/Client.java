package com.asuper.aidldemo.designmode.build;

/**
 * Created by Super on 2016/12/7.
 */

public class Client {

    public static void main(String[] args) {
        Director director = new Director.Build("super")
                .setAge(14)
                .build();
        System.out.println("director name = " + director.getName() + " name = " + director.getAge());
    }
}
