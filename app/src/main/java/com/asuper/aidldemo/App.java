package com.asuper.aidldemo;

import android.app.Application;
import android.os.StrictMode;

import com.antfortune.freeline.*;
import com.od.core.rest.NetParams;

/**
 * Created by Super on 2016/11/10.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FreelineCore.init(this);

        if (BuildConfig.DEBUG) {
            //针对线程策略1
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                        .detectDiskReads()
                        .detectDiskWrites()
                        .detectNetwork()
                        .penaltyLog()
                        .build());

            //针对VM策略
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectLeakedSqlLiteObjects()
                    .detectLeakedClosableObjects()
                    .penaltyLog()
                    .penaltyDeath()
                    .build());
        }

        NetParams.initInstance(new NetParams.Builder(getApplicationContext())
                .isRelease(false)
                .httpHost("http://www.kuaidi100.com")
                .build());
    }
}
