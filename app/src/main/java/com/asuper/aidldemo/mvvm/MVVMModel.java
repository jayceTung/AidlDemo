package com.asuper.aidldemo.mvvm;

import java.util.Random;

/**
 * @author super
 * @date 2020/10/9
 */
public class MVVMModel {

    //模拟查询账号数据
    public void getAccountData(String accountName, MCallback callback){
        Random random = new Random();
        boolean isSuccess = random.nextBoolean();
        if(isSuccess){
            Account account = new Account();
            account.setName(accountName);
            account.setLevel(100);
            callback.onSuccess(account);
        }else {
            callback.onFailed();
        }
    }
}
