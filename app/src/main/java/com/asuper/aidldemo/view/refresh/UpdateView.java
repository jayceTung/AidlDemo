package com.asuper.aidldemo.view.refresh;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.asuper.aidldemo.R;

/**
 * @author super
 * @date 2018/9/19
 */
public class UpdateView extends RelativeLayout {
    private TextView mTvRefresh;
    private ImageView mIvRight;
    private ImageView mIvLoading;
    private AnimationDrawable mDrawableRight;
    private AnimationDrawable mDrawableLoading;

    public UpdateView(Context context) {
        this(context, null);
    }

    public UpdateView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UpdateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        inflate(context, R.layout.view_update_bar, this);
        mTvRefresh = (TextView) findViewById(R.id.tv_title);
        mIvRight = (ImageView) findViewById(R.id.right_content);
        mIvLoading = (ImageView) findViewById(R.id.loading_image);
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int w = MeasureSpec.getSize(widthMeasureSpec);
//        int h = MeasureSpec.getSize(heightMeasureSpec);
//        widthMeasureSpec = heightMeasureSpec = w > h ? MeasureSpec.makeMeasureSpec(w, MeasureSpec.EXACTLY) : MeasureSpec.makeMeasureSpec(h, MeasureSpec.EXACTLY);
//        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
//    }

    public void startAnim(){
        mIvRight.setVisibility(View.VISIBLE);
        mDrawableRight = (AnimationDrawable) mIvRight.getDrawable();
        mDrawableRight.start();
        mIvLoading.setVisibility(View.VISIBLE);
        mDrawableLoading = (AnimationDrawable) mIvLoading.getDrawable();
        mDrawableLoading.start();
    }

    public void stopAnim() {
        if (mDrawableRight != null) {
            mDrawableRight.stop();
        }
        if (mDrawableLoading != null) {
            mDrawableLoading.stop();
        }
    }
}
