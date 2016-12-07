package com.asuper.aidldemo.designmode.Strategy;

/**
 * Created by Super on 2016/12/1.
 */

public class Client {

    public static void main(String[] args) {
        Context context = new Context(new FreeStrategy());
        System.out.println(context.execute(2));

        Context con = new Context(new BusyStrategy());
        System.out.println(con.execute(6));

    }
}
