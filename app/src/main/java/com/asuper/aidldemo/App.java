package com.asuper.aidldemo;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;
import android.util.Log;

import com.antfortune.freeline.FreelineCore;
import com.od.core.rest.NetParams;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by Super on 2016/11/10.
 */

public class App extends Application {
    private static final String TAG = "App";

    public static RefWatcher getRefWatcher(Context context) {
        App application = (App) context.getApplicationContext();
        return application.refWatcher;
    }

    private RefWatcher refWatcher;

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

        refWatcher = LeakCanary.install(this);
        MyCrashHandler.getInstance().init(this);

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
