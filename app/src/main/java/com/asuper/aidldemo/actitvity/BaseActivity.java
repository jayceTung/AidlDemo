package com.asuper.aidldemo.actitvity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.asuper.aidldemo.dagger.DaggerView;

/**
 * Created by Super on 2017/3/17.
 */

public abstract class BaseActivity<T extends DaggerView> extends AppCompatActivity {
    private T daggerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
