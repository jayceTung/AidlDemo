package com.asuper.aidldemo.view.waitivew;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.ColorInt;
import android.support.annotation.Dimension;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.view.View;

/**
 * @author super
 * @date 2018/10/8
 */
public class WaitView extends View {
    private static final String TAG = "WaitView";

    @ColorInt private int mColor;
    @Dimension private int mRadius;
    @IntRange(from = 0, to = 255) private int mAlpha;
    private RectF mRecF;

    private WaitViewController mController;
    private Paint mPaint;

    public WaitView(@NonNull WaitViewController controller) {
        super(controller.getContext());
        mController = controller;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension((int)mRecF.width(), (int)mRecF.height());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(mColor);
        mPaint.setAlpha(mAlpha);
        canvas.drawRoundRect(mRecF, mRadius, mRadius, mPaint);
    }

    public WaitViewController getController() {
        return mController;
    }

    public WaitView color(@ColorInt int color) {
        mColor = color;
        return this;
    }
    public WaitView radius(@Dimension int radius) {
        mRadius = radius;
        return this;
    }
    public WaitView alpha(@IntRange(from=0, to=255) int alpha) {
        mAlpha = alpha;
        return this;
    }
    public WaitView drawRect(RectF drawRect) {
        mRecF = drawRect;
        return this;
    }

}
