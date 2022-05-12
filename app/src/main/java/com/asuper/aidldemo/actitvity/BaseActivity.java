package com.asuper.aidldemo.actitvity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
