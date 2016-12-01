package com.asuper.aidldemo.designmode;

/**
 * Created by Super on 2016/12/1.
 */

public class Context {
    private BaseStrategy mStrategy = null;

    public Context(BaseStrategy mStrategy) {
        this.mStrategy = mStrategy;
    }

    public float execute(int km) {
        return mStrategy.execute(km);
    }
}
