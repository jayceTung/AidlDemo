package com.asuper.aidldemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.asuper.aidldemo.R;

/**
 *
 * 倒计时外圈控件
 * @author super
 * @date 2017/12/14
 */
public class CountDownView extends View {
    private static final String TAG = "CountDownView";
    private static final int REFRESH_TIME = 80;

    private static final int BACKGROUND_COLOR = Color.WHITE;
    private static final int BORDER_COLOR = Color.WHITE;
    private static final String TEXT = " ";
    private static final int TEXT_COLOR = Color.BLACK;

    private int mBackgroundColor;
    private float mBorderWidth;
    private int mBorderColor;
    private String mText;
    private int mTextSize;
    private int mTextColor;

    private Paint mCirclePaint;
    private TextPaint mTextPaint;
    private Paint mBorderPaint;
    private StaticLayout mStaticLayout;
    private float mProgress = 0;
    private CountDownTimer mTimer;

    public CountDownView(Context context) {
        this(context, null);
    }

    public CountDownView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CountDownView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CountDownView);
        mBackgroundColor = ta.getInt(R.styleable.CountDownView_background_color, BACKGROUND_COLOR);
        mBorderWidth = ta.getDimensionPixelSize(R.styleable.CountDownView_border_width, dip2px(15));
        mBorderColor = ta.getInt(R.styleable.CountDownView_border_color, BORDER_COLOR);
        mText = ta.getString(R.styleable.CountDownView_text);
        if (mText == null) {
            mText = TEXT;
        }
        mTextSize = ta.getDimensionPixelSize(R.styleable.CountDownView_text_size, dip2px(15));
        mTextColor = ta.getInt(R.styleable.CountDownView_text_color, TEXT_COLOR);
        ta.recycle();
        initView();
    }

    private void initView() {
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setDither(true);
        mCirclePaint.setColor(mBackgroundColor);
        mCirclePaint.setStyle(Paint.Style.FILL);

        mTextPaint = new TextPaint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setDither(true);
        mTextPaint.setColor(mTextColor);
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setTextAlign(Paint.Align.CENTER);

        mBorderPaint = new Paint();
        mBorderPaint.setAntiAlias(true);
        mBorderPaint.setDither(true);
        mBorderPaint.setColor(mBorderColor);
        mBorderPaint.setStrokeWidth(mBorderWidth);
        mBorderPaint.setStyle(Paint.Style.STROKE);
        mBorderPaint.setStrokeCap(Paint.Cap.ROUND);

        int width = (int) mTextPaint.measureText(mText.substring(0, (mText.length() + 1) / 2));
        mStaticLayout = new StaticLayout(mText, mTextPaint, width, Layout.Alignment.ALIGN_NORMAL,
                1f, 0f, false);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode != MeasureSpec.EXACTLY) {
            widthSize = mStaticLayout.getWidth();
        }
        if (heightMode != MeasureSpec.EXACTLY) {
            heightSize = mStaticLayout.getHeight();
        }
        setMeasuredDimension(widthSize, heightSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int min = Math.min(width, height) / 2;

        canvas.drawCircle(min, min, min - mBorderWidth, mCirclePaint);

        RectF rectF = new RectF(0 + mBorderWidth, 0 + mBorderWidth, min * 2 - mBorderWidth,
                min * 2 - mBorderWidth);
        canvas.drawArc(rectF, -90f, mProgress, false, mBorderPaint);

        canvas.drawText(mText, min, min - mTextPaint.descent() + mTextPaint.getTextSize() / 2, mTextPaint);
        mStaticLayout.draw(canvas);
    }

    public void start(final float millisInFuture) {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
        mTimer = new CountDownTimer((long) millisInFuture, REFRESH_TIME) {
            @Override
            public void onTick(long millisUntilFinished) {
                mProgress = ((millisInFuture - millisUntilFinished) / millisInFuture) * 360;
                mText = String.valueOf((int) Math.ceil(millisUntilFinished / 1000d));
                invalidate();
            }

            @Override
            public void onFinish() {
                mProgress = 360;
                mText = "0";
                invalidate();
            }
        }.start();
    }

    public void cancel() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }


    public int dip2px(float dip) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dip, getContext().getApplicationContext().getResources().getDisplayMetrics()
        );
    }
}