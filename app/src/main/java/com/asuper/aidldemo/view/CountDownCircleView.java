package com.asuper.aidldemo.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import androidx.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.LinearInterpolator;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Super on 2017/2/21.
 */

public class CountDownCircleView extends View {
    private float COUNT = 10;
    private Paint mPaint = new Paint();
    private TextPaint mTextPaint = new TextPaint();
    private volatile float countDownMilli = 10;
    private Timer mTimer;
    private ValueAnimator mValueAnimator;
    private Context mContext;
    private float mAngel;

    // 圆心x坐标
    private int mXCenter;
    // 圆心y坐标
    private int mYCenter;
    private int mRadius = dip2px(40);

    public CountDownCircleView(Context context) {
        this(context, null);
    }

    public CountDownCircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CountDownCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    public int dip2px(float dip) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dip, getContext().getApplicationContext().getResources().getDisplayMetrics()
        );
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);

    }

    private void init() {
        mPaint.setColor(Color.parseColor("#B47191E6"));
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(dip2px(1f));
        mPaint.setStyle(Paint.Style.FILL);

        //初始化绘制文字的画笔
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(Color.parseColor("#ffffff"));
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setTextSize(dip2px(24));
    }

    public void start(float countTime) {
        COUNT = countTime;
        countDownMilli = COUNT + 1;
        if (mTimer != null) {
            mTimer.cancel();
        }
        if (mValueAnimator != null) {
            mValueAnimator.cancel();
        }

        mValueAnimator = ValueAnimator.ofFloat(360f, 0f);
        mValueAnimator.setDuration((int)COUNT * 1000);
        mValueAnimator.setInterpolator(new LinearInterpolator());
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mAngel = (Float) animation.getAnimatedValue();
                invalidate();
            }
        });

        mValueAnimator.start();


        mTimer = new Timer();
        mTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                countDownMilli--;
                if (countDownMilli == 0) {
                    mTimer.cancel();
                }

            }
        }, 0, 1000);


    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (getWidth() <= getHeight()) {
            mXCenter = getWidth() / 2;
            mYCenter = getWidth() / 2;
        } else {
            mXCenter = getHeight() / 2;
            mYCenter = getHeight() / 2;
        }

        float angle = 360 * (countDownMilli / COUNT);
        RectF oval = new RectF(mXCenter - mRadius, mYCenter - mRadius, mXCenter + mRadius, mYCenter + mRadius);
        canvas.drawArc(oval, -90, -mAngel, true, mPaint);

        //绘制中间百分比文字
        Log.i("DMC", COUNT + " " + countDownMilli + " " + angle);
        String percentTxt = String.valueOf((int)countDownMilli);
        //计算文字垂直居中的baseline
        Paint.FontMetricsInt fontMetrics = mTextPaint.getFontMetricsInt();
        float baseline = oval.top + (oval.bottom - oval.top - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;
        canvas.drawText(percentTxt, mXCenter, baseline, mTextPaint);
    }
}
