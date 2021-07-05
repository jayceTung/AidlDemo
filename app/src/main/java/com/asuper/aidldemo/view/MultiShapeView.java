package com.asuper.aidldemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.asuper.aidldemo.R;
import com.asuper.aidldemo.util.Util;

/**
 * @author SuperM1n
 * @brief description
 * @date 2021-07-01
 */
public class MultiShapeView extends ImageView {
    public static final int circle = 0;
    public static final int round = 1;
    Paint paint = new Paint();
    private int radius = 0;
    private int type = 1;

    public MultiShapeView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public MultiShapeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // TODO Auto-generated constructor stub
        initParam(context, attrs);
    }

    public MultiShapeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        initParam(context, attrs);
    }

    void init() {
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
    }

    // 解析参数
    void initParam(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.MultiShapeView);
        if (type == round) {
            radius = typedArray.getInt(R.styleable.MultiShapeView_mv_radius, 0);
            radius = Util.dip2px(getContext(), radius);
        }
        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        Drawable drawable = getDrawable();
        if (null == drawable || type == -1) {
            super.onDraw(canvas);
            return;
        }
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        paint.reset();
        init();
        if (circle == type) {
            int r = 0;
            int left = 0, top = 0;
            if (w > h) {
                r = h / 2;
                left = (w - h) / 2;
            } else {
                r = w / 2;
                top = (h - w) / 2;
            }
            Bitmap b = getCircleBitmap(bitmap, r);
            canvas.drawBitmap(b, left, top, paint);
        } else if (round == type) {
            Bitmap b = getRoundConerBitmap(bitmap, radius);
            canvas.drawBitmap(b, 0, 0, paint);
        }
    }

    // 将Bitmap合成为一个圆角的Bitmap
    public Bitmap getCircleBitmap(Bitmap bitmap, int r) {
        Bitmap b = Bitmap.createBitmap(2 * r, 2 * r, Config.ARGB_8888);
        Paint p = new Paint();
        p.setAntiAlias(true);
        p.setColor(Color.WHITE);
        Canvas canvas = new Canvas(b);
        // 在底层画一个半径为r的圆形
        canvas.drawCircle(r, r, r, p);
        // 设置SRC_IN模式，这种模式取两层图片叠加的并集 展现上面的那一层
        p.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, 0, 0, p);
        return b;
    }

    // 将Bitmap合成为一个圆角的Bitmap
    public Bitmap getRoundConerBitmap(Bitmap bitmap, int round) {
        Bitmap b = Bitmap.createBitmap(getWidth(), getHeight(),
                Config.ARGB_8888);
        Paint p = new Paint();
        p.setAntiAlias(true);
        p.setColor(Color.WHITE);
        Canvas canvas = new Canvas(b);
        // 在底层画一个矩形
        RectF rect = new RectF(0, 0, getWidth(), getHeight());
        canvas.drawRoundRect(rect, round, round, p);
        p.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        // 设置SRC_IN模式，这种模式取两层图片叠加的并集 展现上面的那一层
        canvas.drawBitmap(bitmap, 0, 0, p);
        return b;
    }
}