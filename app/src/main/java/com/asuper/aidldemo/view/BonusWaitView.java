package com.asuper.aidldemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.CountDownTimer;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.asuper.aidldemo.R;

/**
 * @author super
 * @date 2020/5/27
 */
public class BonusWaitView extends RelativeLayout {
    private static final int REFRESH_TIME = 60;

    private Context mContext;
    private int mBackgroundColor;
    private int mBoardColor;
    private int mBoardWidth;
    private int mRadius;
    private float mProgress = 0;

    private Paint mCirclePaint;
    private Paint mBoardPaint;
    private TextView textView;
    private CountDownTimer mTimer;

    public BonusWaitView(Context context) {
        this(context, null);
    }

    public BonusWaitView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BonusWaitView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CountDownView);
        ta.recycle();
        initVariable();
    }

    private void initVariable() {
        mRadius = dip2px(42);
        mBackgroundColor = mContext.getResources().getColor(R.color.kk_4d000000);
        mBoardColor = mContext.getResources().getColor(R.color.kk_ffb50d);
        mBoardWidth = dip2px(5);

        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setDither(true);
        mCirclePaint.setColor(mBackgroundColor);
        mCirclePaint.setStyle(Paint.Style.FILL);

        mBoardPaint = new Paint();
        mBoardPaint.setAntiAlias(true);
        mBoardPaint.setDither(true);
        mBoardPaint.setColor(mBoardColor);
        mBoardPaint.setStrokeWidth(mBoardWidth);
        mBoardPaint.setStyle(Paint.Style.STROKE);



        textView = new TextView(mContext);
        textView.setBackgroundResource(R.drawable.kk_btn_bonus);
        LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        params.width = dip2px(137);
        params.height = dip2px(137);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        addView(textView, params);
        textView.setOnClickListener(v -> {
            Log.i("dmc", "ddddddd");
        });
        setWillNotDraw(false);
    }

    public void start(long millisInFuture) {
        setVisibility(View.VISIBLE);
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
        mTimer = new CountDownTimer((long) millisInFuture, REFRESH_TIME) {
            @Override
            public void onTick(long millisUntilFinished) {
                mProgress = ((millisInFuture - millisUntilFinished) / (float)millisInFuture) * 360;
                invalidate();
                Log.i("dmc", mProgress + "");
            }

            @Override
            public void onFinish() {
                mProgress = 360;
                invalidate();
            }
        }.start();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int min = Math.min(width, height) / 2;

        canvas.drawCircle(min, min, mRadius, mCirclePaint);

        int boradRadius = mRadius - dip2px(5);

        RectF oval = new RectF();
        oval.left = min - boradRadius;
        oval.top = min - boradRadius;
        oval.right = boradRadius * 2 + (min - boradRadius);
        oval.bottom = boradRadius * 2 + (min - boradRadius);
        if (mProgress > 0) {
            canvas.drawArc(oval, -90f, mProgress, false, mBoardPaint);
        }
    }

    public int dip2px(float dip) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dip, getContext().getApplicationContext().getResources().getDisplayMetrics()
        );
    }
}
