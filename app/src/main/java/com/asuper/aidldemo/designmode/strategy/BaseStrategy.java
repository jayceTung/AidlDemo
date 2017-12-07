package com.asuper.aidldemo.designmode.strategy;

/**
 * Created by Super on 2016/12/1.
 */

public interface BaseStrategy {

    /**
     * @return 计价
     * @param km 里程
     */
    float execute(int km);
}
