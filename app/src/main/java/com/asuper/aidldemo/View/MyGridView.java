package com.asuper.aidldemo.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.GridView;

import com.asuper.aidldemo.R;

/**
 * Created by Super on 2017/4/18.
 */

public class MyGridView extends GridView {
    private Context mContext;

    public MyGridView(Context context) {
        this(context, null);
    }


    public MyGridView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }


    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        View view = getChildAt(0);
        int column = getWidth() / view.getWidth();

        Log.i("MyGridView", "colunm = " + column);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(mContext.getResources().getColor(R.color.colorPrimary));

        for (int i = 1; i < column; i++) {
            canvas.drawLine(10, getHeight() / 2, getWidth() - 10, getHeight() / 2, paint);
        }
    }
}
