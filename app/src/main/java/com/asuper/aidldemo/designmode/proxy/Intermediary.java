package com.asuper.aidldemo.designmode.proxy;

/**
 * @author super
 * @date 2018/10/8
 */
public class Intermediary implements Home {
    private Custom custom;

    public Intermediary() {
        this.custom = new Custom();
    }

    @Override
    public void selectHome() {
        System.out.println("找房源");
        this.custom.selectHome();
        System.out.println("签合同");
    }
}
