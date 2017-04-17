package com.asuper.aidldemo.dagger;

import com.asuper.aidldemo.actitvity.DaggerActivity;

import dagger.Component;

/**
 * Created by Super on 2017/4/17.
 */

@Component(modules = DaggerModule.class)
public interface GetComponent {

    void inject(DaggerActivity activity);
}
