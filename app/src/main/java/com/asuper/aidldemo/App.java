package com.asuper.aidldemo;

import android.app.Application;

import com.antfortune.freeline.FreelineCore;

/**
 * Created by Super on 2016/11/10.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FreelineCore.init(this);
    }
}
