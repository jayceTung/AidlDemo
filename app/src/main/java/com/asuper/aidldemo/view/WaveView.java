package com.asuper.aidldemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author super
 * @date 2017/12/5
 */
public class WaveView extends View {
    private static final String TAG = "WaveView";

    private Paint mPaint;
    private int mMaxRadius;
    private long mDuration = 2000;
    private int mSpeed = 2000;
    private Interpolator mInterpolator = new LinearInterpolator();
    private int mInitialRadius = 1;
    private int lengthX;
    private int lengthY;
    private int mCx;
    private int mCy;
    private long mLastCreateTime;
    private boolean mIsRunning;
    private List<Circle> mCircleList = new ArrayList<>();

    private Runnable mCreateCircle = new Runnable() {
        @Override
        public void run() {
            if (mIsRunning) {
                newCircle();
                postDelayed(mCreateCircle, mSpeed);
            }
        }
    };

    private void newCircle() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - mLastCreateTime < mSpeed) {
            return;
        }
        Circle circle = new Circle(getRandomX(), getRandomY());
        mCircleList.add(circle);
        invalidate();
        mLastCreateTime = currentTime;
    }

    private int getRandomX() {
        mCx = (int) (Math.random() * lengthX + 1);
        return mCx;
    }

    private int getRandomY() {
        if (mCx >= lengthX) {
            mCy = (int) (Math.random() * lengthY + 1);
        } else {
            mCy = lengthY;
        }
        return mCy;
    }


    /**
     * 开始
     */
    public void start() {
        if (!mIsRunning) {
            mIsRunning = true;
            mCreateCircle.run();
        }
    }

    /**
     * 停止
     */
    public void stop() {
        mIsRunning = false;
    }

    public WaveView(Context context) {
        this(context, null);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.WHITE);
        mPaint.setAntiAlias(false);
        mPaint.setStrokeWidth(9);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        lengthX = w;
        lengthY = h;
        mMaxRadius = Math.min(w, h);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Iterator<Circle> iterator = mCircleList.iterator();
        while (iterator.hasNext()) {
            Circle circle = iterator.next();
            if (System.currentTimeMillis() - circle.mCreateTime < mDuration) {
                mPaint.setAlpha(circle.getAlpha());
                canvas.drawCircle(circle.getCx(), circle.getCy(), circle.getCurrentRadius(), mPaint);
            } else {
                iterator.remove();
            }
        }
        if (mCircleList.size() > 0) {
            postInvalidateDelayed(10);
        }
    }

    private class Circle {
        private long mCreateTime;
        private int cx;
        private int cy;

        public Circle(int cx, int cy) {
            this.mCreateTime = System.currentTimeMillis();
            this.cx = cx;
            this.cy = cy;
        }

        public int getAlpha() {
            float percent = (System.currentTimeMillis() - mCreateTime) * 1.0f / mDuration;
            return (int) ((1.0f - mInterpolator.getInterpolation(percent)) * 255);
        }

        public float getCurrentRadius() {
            float percent = (System.currentTimeMillis() - mCreateTime) * 1.0f / mDuration;
            return mInitialRadius + mInterpolator.getInterpolation(percent) * (mMaxRadius - mInitialRadius);
        }

        public int getCx() {
            return cx;
        }

        public int getCy() {
            return cy;
        }
    }
}
