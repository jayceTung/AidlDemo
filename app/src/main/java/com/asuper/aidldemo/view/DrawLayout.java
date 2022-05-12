package com.asuper.aidldemo.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.asuper.aidldemo.R;
import com.asuper.aidldemo.java.PointBean;

import java.util.ArrayList;

/**
 * @author super
 * @date 2018/10/22
 */
public class DrawLayout extends LinearLayout {
    private static final String TAG = "DrawLayout";
    private Context mContext;

    private DrawView mDv1;
    private DrawView mDv2;
    private DrawView mDv3;
    private DrawView mDv4;
    private DrawView mDv5;
    private DrawView mDv6;
    private DrawView mDvCenter;
    private ArrayList<PointBean> list;

    public DrawLayout(Context context) {
        this(context, null);
    }

    public DrawLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.layout_draw_view, this);
        mDv1 = (DrawView) findViewById(R.id.dv_1);
        mDv2 = (DrawView) findViewById(R.id.dv_2);
        mDv3 = (DrawView) findViewById(R.id.dv_3);
        mDv4 = (DrawView) findViewById(R.id.dv_4);
        mDv5 = (DrawView) findViewById(R.id.dv_5);
        mDv6 = (DrawView) findViewById(R.id.dv_6);
        mDvCenter = (DrawView) findViewById(R.id.dv_center);
        list = new ArrayList<>();
    }

    public void shuffle() {
        AnimatorSet animSet = new AnimatorSet();
        animSet.playTogether(getAniSet(mDv1), getAniSet(mDv2), getAniSet(mDv3), getAniSet(mDv4),
                getAniSet(mDv5), getAniSet(mDv6));
        animSet.start();
    }

    public void deal() {
        AnimatorSet animSet = new AnimatorSet();
        animSet.playTogether(getAniSet2(mDv6, 6), getAniSet2(mDv5, 5),
                getAniSet2(mDv4, 4), getAniSet2(mDv3, 3), getAniSet2(mDv2, 2), getAniSet2(mDv1, 1));
        animSet.start();
    }

    private AnimatorSet getAniSet(DrawView view) {
        Log.i(TAG, "x = " + mDvCenter.getLeft() + " y = " + mDvCenter.getTop());
        Log.i(TAG, "x = " + view.getLeft() + " y = " + view.getTop());
        float x = mDvCenter.getX() - view.getX();
        float y = mDvCenter.getY()- view.getY();
        PointBean bean = new PointBean();
        bean.x = x;
        bean.y = y;
        list.add(bean);
        Log.i(TAG, "x = " + x + " y = " + y);
        ObjectAnimator objectTranslationX = ObjectAnimator.ofFloat(view, "translationX", 0, x);
        ObjectAnimator objectTranslationY = ObjectAnimator.ofFloat(view, "translationY", 0, y);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectTranslationX, objectTranslationY);
        animatorSet.setDuration(300);
        return animatorSet;
    }

    private AnimatorSet getAniSet2(DrawView view, int num) {
        Log.i(TAG, "x = " + mDvCenter.getLeft() + " y = " + mDvCenter.getTop());
        Log.i(TAG, "x = " + view.getLeft() + " y = " + view.getTop());
        float cX = mDvCenter.getX();
        float vX = view.getX();
        float cY = mDvCenter.getY();
        float vY = view.getY();
        PointBean bean = list.get(num - 1);
        float x = bean.x;
        float y = bean.y;
        Log.i(TAG, "x = " + x + " y = " + y);
        ObjectAnimator objectTranslationX = ObjectAnimator.ofFloat(view, "translationX", x, 0);
        ObjectAnimator objectTranslationY = ObjectAnimator.ofFloat(view, "translationY", y, 0);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectTranslationX, objectTranslationY);
        animatorSet.setDuration(300);
        return animatorSet;
    }


}
