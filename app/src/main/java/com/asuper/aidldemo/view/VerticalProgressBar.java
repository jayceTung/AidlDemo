package com.asuper.aidldemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author super
 * @date 2019-06-24
 */
public class VerticalProgressBar extends View {
    /**
     * 渐变颜色
     */
    private static final int[] SECTION_COLORS = {0xff2EE1FF, 0xff2FA4F7};
    /**
     * 进度条最大值
     */
    private float maxCount;
    /**
     * 进度条当前值
     */
    private float currentCount;
    /**
     * 画笔
     */
    private Paint mPaint;
    private int mWidth, mHeight;

    public VerticalProgressBar(Context context) {
        super(context);
    }

    public VerticalProgressBar(Context context, AttributeSet attrs,
                               int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public VerticalProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        int round = mHeight / 2;
        //蓝色边框
        //绘制阴影效果
        int beginP = dipToPx(0);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(0xff00a3d5);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setShadowLayer(100, 0, 0, Color.WHITE);

        float section = currentCount / maxCount;
        RectF rectProgressBg = new RectF(beginP + 3, mHeight * (1- section) + 3, mWidth -3 , mHeight - 3);
        if (section <= 1.0f / 3.0f) {
            if (section != 0.0f) {
                mPaint.setColor(SECTION_COLORS[0]);
            } else {
                mPaint.setColor(Color.TRANSPARENT);
            }
        } else {
            int count = 2;
            int[] colors = new int[count];
            System.arraycopy(SECTION_COLORS, 0, colors, 0, count);
            float[] positions = new float[count];
            if (count == 2) {
                positions[0] = 0.0f;
                positions[1] = 1.0f - positions[0];
            } else {
                positions[0] = 0.0f;
                positions[1] = (maxCount / 3) / currentCount;
                positions[2] = 1.0f - positions[0] * 2;
            }
            positions[positions.length - 1] = 1.0f;
            //线性渲染
            LinearGradient shader = new LinearGradient(3, 3, (mWidth - 3) * section, mHeight - 3, colors, null, Shader.TileMode.MIRROR);
            mPaint.setShader(shader);
        }
        canvas.drawRoundRect(rectProgressBg, round, round, mPaint);
    }

    /**
     * dp转换成像素
     *
     * @param dip
     * @return
     */
    private int dipToPx(int dip) {
        float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f * (dip >= 0 ? 1 : -1));
    }

    /***
     * 设置最大的进度值
     * @param maxCount
     */
    public void setMaxCount(float maxCount) {
        this.maxCount = maxCount;
    }

    /***
     * 设置当前的进度值
     * @param currentCount
     */
    public void setCurrentCount(float currentCount) {
        this.currentCount = currentCount > maxCount ? maxCount : currentCount;
        invalidate();
    }

    /**
     * UNSPECIFIED 父容器没有对当前View有任何限制，当前View可以任意取尺寸
     * EXACTLY 当前的尺寸就是当前View应该取的尺寸
     * AT_MOST 当前尺寸是当前View能取的最大尺寸
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthSpecMode == MeasureSpec.EXACTLY || widthSpecMode == MeasureSpec.AT_MOST) {
            mWidth = widthSpecSize;
        } else {
            mWidth = 0;
        }
        if (heightSpecMode == MeasureSpec.AT_MOST || heightSpecMode == MeasureSpec.UNSPECIFIED) {
            mHeight = dipToPx(25);
        } else {
            mHeight = heightSpecSize;
        }
        setMeasuredDimension(mWidth, mHeight);
    }
}
