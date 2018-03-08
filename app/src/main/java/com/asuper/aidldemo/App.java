package com.asuper.aidldemo;

import android.app.Application;
import android.os.StrictMode;
import android.util.Log;

import com.antfortune.freeline.*;
import com.od.core.rest.NetParams;

/**
 * Created by Super on 2016/11/10.
 */

public class App extends Application {
    private static final String TAG = "App";

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

    @Override
    public void onLowMemory() {
        Log.i(TAG, "onLowMemory");
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        Log.i(TAG, "onTrimMemory");
        super.onTrimMemory(level);
    }
}
