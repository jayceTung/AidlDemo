package com.asuper.aidldemo.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.asuper.aidldemo.R;

import java.util.List;

/**
 * @author super
 * @date 2018/9/25
 */
public class TabContainerView extends FrameLayout implements View.OnClickListener {
    private static final String TAG = "TabContainerView";
    private Context mContext;
    private int prePosition,position;
    private onTabSelectedListener mListener;
    private LinearLayout llContainer;
    private ImageView itemBgView;
    private int currentPosition;
    private int offset = 0;
    private int mTotalWidth;
    public TabContainerView(Context context) {
        this(context,null);
    }

    public TabContainerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public interface onTabSelectedListener{
        void onTabSelected(int position, int totalWidth);
    }

    public void setOnTabSelectedListener(onTabSelectedListener listener){
        mListener = listener;
    }

    public void updateTabItemViews(List<String> tabList, int defaultPosition) {
        if(tabList.size() < 2) {
            setVisibility(GONE);
            return;
        }
        if (tabList != null && tabList.size() > 0) {
            if (defaultPosition > tabList.size()) {
                defaultPosition = tabList.size() - 1;
            }
            if (getChildCount() != 0) {
                removeAllViews();
                //偏移量置为0
                offset = 0;
            }
            llContainer = new LinearLayout(mContext);
            for (int i = 0; i < tabList.size(); i++) {
                TextView tvTabItem = new TextView(mContext);
                tvTabItem.setTextSize(20);
                tvTabItem.setOnClickListener(this);
                tvTabItem.setText(tabList.get(i));
                tvTabItem.setPadding(10, 0, 10, 0);
                tvTabItem.setTag(i);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.weight = 1;
                tvTabItem.setLayoutParams(params);
                llContainer.addView(tvTabItem);
                mTotalWidth += getTabItemWidth(tvTabItem);
            }
            addView(llContainer);
            addView(initTabBgView(defaultPosition));
        }
    }
    @Override
    public void onClick(View v) {
        int distance = 0;
        currentPosition = (int) v.getTag();
        position = currentPosition;
        if(currentPosition == prePosition){
            return;
        }
        if (mListener != null) {
            mListener.onTabSelected(position,mTotalWidth);
        }
        for (int i = 0; i < currentPosition; i++) {
            distance += getTabItemWidth(llContainer.getChildAt(i));
        }
        beginTranslateAndScale(itemBgView,offset,distance,prePosition,currentPosition);
        prePosition = currentPosition;
    }

    /**
     * 设置tab的背景
     * @param position 需要显示背景的tab位置
     * @return
     */
    public ImageView initTabBgView(int position){
        itemBgView = new ImageView(mContext);
        Drawable drawable = getResources().getDrawable(R.drawable.kk_bg_select_disenable);
        //设置偏移的位置
        for (int i = 0; i < position; i++) {
            offset += getTabItemWidth(llContainer.getChildAt(i));
        }
        itemBgView.setTranslationX(offset);
        prePosition = position;
        setItemBgWidth(position);
        itemBgView.setImageDrawable(drawable);
        return itemBgView;
    }

    public void setItemBgWidth(int position){
        TextView currentChildView = (TextView) llContainer.getChildAt(position);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.width = getTabItemWidth(currentChildView);
        params.height = 60;
        itemBgView.setLayoutParams(params);
    }

    /**
     * 获取每个tab的宽度
     * @param tabItem
     * @return
     */
    public int getTabItemWidth(View tabItem){
        int measureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        tabItem.measure(measureSpec,measureSpec);
        return tabItem.getMeasuredWidth();
    }

    public void beginTranslateAndScale(View view,int from,int to,int prePosition,int currentPosition){
        view.setTranslationX(0);
        offset = to;
        Log.d(TAG, "beginTranslateAndScale: "+prePosition+"--"+currentPosition);
        int startWidth = getTabItemWidth(llContainer.getChildAt(prePosition));
        int endWidth = getTabItemWidth(llContainer.getChildAt(currentPosition));
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "translationX", from, to);
        final ValueAnimator scaleAnimator = ValueAnimator.ofInt(startWidth, endWidth);
        scaleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int) scaleAnimator.getAnimatedValue();
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.width = animatedValue;
                params.height = 60;
                itemBgView.setLayoutParams(params);
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleAnimator,objectAnimator);
        animatorSet.setDuration(1000);
        animatorSet.start();
    }
}
