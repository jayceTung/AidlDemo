package com.asuper.aidldemo.dagger;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Super on 17/4/13.
 */

@Module
public class MainModule {

    @Provides
    public User getUser() {
        return new User("tom");
    }
}
