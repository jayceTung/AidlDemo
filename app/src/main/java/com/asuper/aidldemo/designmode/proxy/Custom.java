package com.asuper.aidldemo.designmode.proxy;

/**
 * @author super
 * @date 2018/10/8
 */
public class Custom implements Home {
    @Override
    public void selectHome() {
        System.out.println("选择房子");
    }
}
