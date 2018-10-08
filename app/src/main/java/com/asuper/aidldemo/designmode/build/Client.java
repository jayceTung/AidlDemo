package com.asuper.aidldemo.designmode.build;

import com.asuper.aidldemo.designmode.decorator.Component;
import com.asuper.aidldemo.designmode.decorator.ConcreateComponent;
import com.asuper.aidldemo.designmode.decorator.ConcreateDecorator;
import com.asuper.aidldemo.designmode.proxy.Home;
import com.asuper.aidldemo.designmode.proxy.Intermediary;

/**
 * Created by Super on 2016/12/7.
 */

public class Client {

    public static void main(String[] args) {
        Director director = new Director.Build("super")
                .setAge(14)
                .build();
        System.out.println("director name = " + director.getName() + " name = " + director.getAge());

        Component component = new ConcreateDecorator(new ConcreateComponent());
        component.biu();

        Home home = new Intermediary();
        home.selectHome();
    }
}
