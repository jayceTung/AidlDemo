package com.asuper.aidldemo.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Super on 2016/11/15.
 */

public class PaintView extends View {
    private static final String TAG = "PainView";

    private Paint paint;

    public PaintView(Context context) {
        this(context, null);
    }

    public PaintView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PaintView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d(TAG, "onDraw");
        super.onDraw(canvas);
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
        Path path = new Path();
        path.moveTo(0, 0);
//        path.lineTo(100, 100);
        path.lineTo(150, 190);
        path.lineTo(400, 240);
        canvas.drawPath(path, paint);
        canvas.save();
//        canvas.translate(0, 50);
//        canvas.drawTextOnPath("你好",path, 10, 10, paint);
        canvas.restore();
    }
}
