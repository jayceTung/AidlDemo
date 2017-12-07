package com.asuper.aidldemo.designmode.strategy;

/**
 * Created by Super on 2016/12/1.
 */

public class BusyStrategy implements BaseStrategy {

    @Override
    public float execute(int km) {
        if (km < 5) {
            return 1f;
        } else if (km < 10) {
            return 2f;
        } else if (km >= 10) {
            return 3f;
        }
        return 0;
    }
}
