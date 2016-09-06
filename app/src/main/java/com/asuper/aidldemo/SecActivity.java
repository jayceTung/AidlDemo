package com.asuper.aidldemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by Super on 2016/9/6.
 */
public class SecActivity extends Activity {
    private static final String TAG = SecActivity.class.getSimpleName();

    ImageView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        setContentView(R.layout.activity_sec);
        view = (ImageView) findViewById(R.id.circle_view);
        Glide.with(this).load("http://ures.kktv8.com/kktv/activity/image/575/20130424151316_797.jpg!160")
            .asBitmap().into(view);
    }
}
