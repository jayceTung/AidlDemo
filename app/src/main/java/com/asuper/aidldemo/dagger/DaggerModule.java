package com.asuper.aidldemo.dagger;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Super on 2017/4/17.
 */

@Module
public class DaggerModule {
    private DaggerView mDaggerView;

    public DaggerModule(DaggerView mDaggerView) {
        this.mDaggerView = mDaggerView;
    }

    @Provides
    DaggerView getmDaggerView() {
        return mDaggerView;
    }
}
