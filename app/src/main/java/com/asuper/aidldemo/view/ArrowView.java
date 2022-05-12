package com.asuper.aidldemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.asuper.aidldemo.R;

/**
 * @author super
 * @date 2018/5/31
 */
public class ArrowView extends View {
    private static final String TAG = "ArrowView";

    private Context mContext;

    private Paint mPaint;
    private Path mPath;
    private int mArrowColor;
    private float mArrowWidth;
    private int mDefaultColor = Color.parseColor("#EDEDED");
    // unit dp
    private float mDefaultWidth = 12;
    private int pivotX;
    private int pivotY;

    public ArrowView(Context context) {
        this(context, null);
    }

    public ArrowView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ArrowView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ArrowView);
        mArrowWidth = array.getDimension(R.styleable.ArrowView_width, mDefaultWidth);
        mArrowColor = array.getColor(R.styleable.ArrowView_color, mDefaultColor);
        array.recycle();
        initVariable();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.i(TAG, "onDraw");
        super.onDraw(canvas);

        int averageWidth = getWidth() / 4;
        int averageHeight = getHeight() / 4;
        pivotX = getWidth() / 2;
        pivotY = getHeight() / 2;
        mPath.moveTo(pivotX + averageWidth, pivotY + 1);
        mPath.lineTo(averageWidth, averageHeight);
        mPath.moveTo(pivotX + averageWidth, pivotY - 1);
        mPath.lineTo(averageWidth, pivotY + averageHeight);

        canvas.drawPath(mPath, mPaint);
        canvas.save();
        canvas.restore();
    }

    private void initVariable() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(mArrowColor);
        mPaint.setStrokeWidth(mArrowWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setDither(true);

        mPath = new Path();
    }

    public int dp2px(float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                mContext.getResources().getDisplayMetrics());
    }
}
