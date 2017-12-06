package com.asuper.aidldemo.view.dispatchview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * @author super
 * @date 2017/12/6
 */
public class SuperViewGroup extends RelativeLayout {
    private static final String TAG = "SuperViewGroup";

    public SuperViewGroup(Context context) {
        super(context);
    }

    public SuperViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SuperViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i(TAG, "dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i(TAG, "onInterceptTouchEvent");
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, "onTouchEvent");
        return super.onTouchEvent(event);
    }
}
