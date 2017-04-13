package com.asuper.aidldemo.dagger;

import com.asuper.aidldemo.actitvity.DaggerActivity;

import dagger.Component;

/**
 * Created by Super on 17/4/13.
 */

@Component(modules = MainModule.class)
public interface MianConponet {
    void inject(DaggerActivity activity);
}
