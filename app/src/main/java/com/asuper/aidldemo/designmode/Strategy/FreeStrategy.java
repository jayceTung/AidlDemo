package com.asuper.aidldemo.designmode.Strategy;

/**
 * Created by Super on 2016/12/1.
 */

public class FreeStrategy implements BaseStrategy {


    @Override
    public float execute(int km) {
        if (km < 5) {
            return 1f;
        } else if (km < 10) {
            return 2f;
        } else if (km >= 10) {
            return 3f;
        }
        return 0f;
    }
}
