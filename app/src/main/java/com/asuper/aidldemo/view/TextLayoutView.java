package com.asuper.aidldemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by super on 2017/8/8.
 */

public class TextLayoutView extends View {
    private static final String TAG = "TextLayoutView";

    private Layout mLayout;

    public TextLayoutView(Context context) {
        this(context, null);
    }

    public TextLayoutView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextLayoutView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setLayout(Layout layout) {
        this.mLayout = layout;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        if (mLayout != null) {
            canvas.translate(getPaddingLeft(), getPaddingTop());
            mLayout.draw(canvas);
        }

        canvas.restore();

    }
}
