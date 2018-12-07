package com.asuper.aidldemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * @author super
 * @date 2018-12-07
 */
public abstract class BaseActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        initView();
        initEvent();
    }

    protected abstract int getContentView();

    protected void initView() {
        Log.i(TAG, "initView()");
    }

    protected void initEvent() {
        Log.i(TAG, "initEvent");
    }
}
