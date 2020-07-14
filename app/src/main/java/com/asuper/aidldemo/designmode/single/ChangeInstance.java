package com.asuper.aidldemo.designmode.single;

/**
 * @author super
 * @date 2020/7/14
 */
public class ChangeInstance {
    private static ChangeInstance mChangeInstance;

    private ChangeInstance() {
    }

    public static ChangeInstance getInstance() {
        if (mChangeInstance == null) {
            synchronized (ChangeInstance.class) {
                if (mChangeInstance == null) {
                    mChangeInstance = new ChangeInstance();
                }
            }
        }
        return mChangeInstance;
    }

    public String getMethod() {
        return "getMethod";
    }
}
