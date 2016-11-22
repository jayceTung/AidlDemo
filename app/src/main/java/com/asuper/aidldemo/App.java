package com.asuper.aidldemo;

import android.app.Application;
import android.os.StrictMode;

import com.antfortune.freeline.*;

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
    }
}
