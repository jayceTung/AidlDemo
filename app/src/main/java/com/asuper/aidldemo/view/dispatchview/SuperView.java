package com.asuper.aidldemo.view.dispatchview;

import android.content.Context;
import android.graphics.Color;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author super
 * @date 2017/12/6
 */
public class SuperView extends View {
    private static final String TAG = "SuperView";

    public SuperView(Context context) {
        this(context, null);
    }

    public SuperView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SuperView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBackgroundColor(Color.WHITE);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.i(TAG, "dispatchTouchEvent");
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, "onTouchEvent");
        return super.onTouchEvent(event);
    }
}
