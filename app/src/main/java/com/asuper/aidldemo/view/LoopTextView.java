package com.asuper.aidldemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

import java.lang.reflect.Field;

/**
 * @author super
 * @date 2019-01-16
 */
public class LoopTextView extends TextView {
    public static final byte MARQUEE_STOPPED = 0x0;
    public static final byte MARQUEE_STARTING = 0x1;
    public static final byte MARQUEE_RUNNING = 0x2;
    private byte mCurrentStatus = MARQUEE_STOPPED;

    public LoopTextView(Context context) {
        super(context);
    }

    public LoopTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoopTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean isFocused() {
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        byte newState = getState();
        if (mCurrentStatus != newState && onScrollingStateChangedListener != null) {
            onScrollingStateChangedListener.onStateChanged(mCurrentStatus = newState);
        }
    }

    private byte getState() {
        try {
            Field marquee = TextView.class.getDeclaredField("mMarquee");
            marquee.setAccessible(true);
            Object marqueeObject = marquee.get(this);
            Field status = marqueeObject.getClass().getDeclaredField("mStatus");
            status.setAccessible(true);
            return (byte) status.get(marqueeObject);
        } catch (Exception e) {
            e.printStackTrace();
            return MARQUEE_STOPPED;
        }
    }

    private OnScrollingStateChangedListener onScrollingStateChangedListener;

    public void setOnScrollingStateChangedListener(OnScrollingStateChangedListener listener) {
        onScrollingStateChangedListener = listener;
    }

    public interface OnScrollingStateChangedListener {
        void onStateChanged(byte state);
    }
}
