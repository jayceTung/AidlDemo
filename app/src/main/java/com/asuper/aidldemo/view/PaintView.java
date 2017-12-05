package com.asuper.aidldemo.view;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.asuper.aidldemo.R;

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
        Paint paint_blue = new Paint();                        //绘制蓝色的环
        paint_blue.setColor(Color.BLUE);
        paint_blue.setStyle(Paint.Style.STROKE);
        paint_blue.setStrokeWidth(10);
        canvas.drawCircle(110,150,60,paint_blue);

        Paint paint_yellow = new Paint();                //绘制黄色的环
        paint_yellow.setColor(Color.YELLOW);
        paint_yellow.setStyle(Paint.Style.STROKE);
        paint_yellow.setStrokeWidth(10);
        canvas.drawCircle((float)175.5, 210, 60, paint_yellow);

        Paint paint_black = new Paint();                   //绘制黑色的环
        paint_black.setColor(Color.BLACK);
        paint_black.setStyle(Paint.Style.STROKE);
        paint_black.setStrokeWidth(10);
        canvas.drawCircle(245, 150, 60, paint_black);

        Paint paint_green = new Paint();                  //绘制绿色的环
        paint_green.setColor(Color.GREEN);
        paint_green.setStyle(Paint.Style.STROKE);
        paint_green.setStrokeWidth(10);
        canvas.drawCircle(311, 210, 60, paint_green);

        Paint paint_red = new Paint();                       //绘制红色的环
        paint_red.setColor(Color.RED);
        paint_red.setStyle(Paint.Style.STROKE);
        paint_red.setStrokeWidth(10);
        canvas.drawCircle(380, 150, 60, paint_red);

        Paint paint_string = new Paint();                   //绘制字符串
        paint_string.setColor(Color.BLUE);
        paint_string.setTextSize(20);
        canvas.drawText("Welcome to Beijing", 245, 310, paint_string);

        Paint paint_line = new Paint();                       //绘制直线
        paint_line.setColor(Color.BLUE);
        canvas.drawLine(240, 310, 425, 310, paint_line);

        Paint paint_text = new Paint();                      //绘制字符串
        paint_text.setColor(Color.BLUE);
        paint_text.setTextSize(20);
        canvas.drawText("北京欢迎您", 275, 330, paint_text);

        //绘制福娃图片
        canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.bang_start_tip), 35, 340, paint_line);
        canvas.save();
        canvas.restore();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }
}
