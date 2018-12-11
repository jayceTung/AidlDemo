package com.asuper.aidldemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.asuper.aidldemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author super
 * @date 2018-12-11
 */
public class LoopView extends LinearLayout {
    private static final String TAG = "LoopView";

    private int mTextColor;
    private int mTextSize;
    private Drawable mDrawable;
    private ArrayList<String> mList = new ArrayList<>();
    private int index = 0;//textview上下滚动下标
    private Handler handler = new Handler();
    private boolean isFlipping = false; // 是否启用预警信息轮播

    private TextSwitcher mTsView;
    private ImageView mIvIcon;

    public LoopView(Context context) {
        this(context, null);
    }

    public LoopView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoopView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(HORIZONTAL);
        initAttr(context, attrs);
        initView();
    }

    private void initAttr(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.LoopView);
        if (ta == null) return;

        for (int i = 0; i < ta.getIndexCount(); i++) {
            int attr = ta.getIndex(i);
            if (attr == R.styleable.LoopView_lv_text_color) {
                mTextColor = ta.getColor(R.styleable.LoopView_lv_text_color, 0);
            } else if (attr == R.styleable.LoopView_lv_text_size) {
                mTextSize = ta.getDimensionPixelOffset(R.styleable.LoopView_lv_text_size, 1);
            } else if (attr == R.styleable.LoopView_lv_text_icon) {
                mDrawable = ta.getDrawable(R.styleable.LoopView_lv_text_icon);
            }
        }
        ta.recycle();
    }

    private void initView() {
        mIvIcon = newImageView();
        mTsView = newTextSwitcher();
        addView(mIvIcon);
        addView(mTsView);
    }

    private ImageView newImageView() {
        ImageView iv = new ImageView(getContext());
        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.CENTER_VERTICAL;
        iv.setImageDrawable(mDrawable);
        iv.setLayoutParams(lp);
        return iv;
    }

    private TextSwitcher newTextSwitcher() {
        TextSwitcher ts = new TextSwitcher(getContext());
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        lp.gravity = Gravity.CENTER_VERTICAL;
        ts.setLayoutParams(lp);

        ts.setInAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_bottom));
        ts.setOutAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.slide_out_top));
        ts.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(getContext());
                textView.setSingleLine();
                textView.setTextSize(mTextSize);//字号
                textView.setTextColor(mTextColor);
                textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                textView.setSingleLine();
                textView.setGravity(Gravity.CENTER);
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                params.gravity = Gravity.CENTER;
                textView.setLayoutParams(params);
                textView.setPadding(25, 0, 25, 0);
                textView.setSelected(true);
                return textView;
            }
        });
        return ts;
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (!isFlipping) {
                return;
            }
            index++;
            mTsView.setText(mList.get(index % mList.size()));
            if (index == mList.size()) {
                index = 0;
            }
            startFlipping();
        }
    };
    //开启信息轮播
    public void startFlipping() {
        if (mList.size() > 1) {
            handler.removeCallbacks(runnable);
            isFlipping = true;
            handler.postDelayed(runnable, 3000);
        }
    }
    //关闭信息轮播
    public void stopFlipping() {
        if (mList.size() > 1) {
            isFlipping = false;
            handler.removeCallbacks(runnable);
        }
    }
    //设置数据
    public void addData(List<String> list) {
        stopFlipping();
        mList.clear();
        mList.addAll(list);
        if (mList.size() == 1) {
            mTsView.setText(mList.get(0));
            index = 0;
        }
        if (mList.size() > 1) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mTsView.setText(mList.get(0));
                    index = 0;
                }
            }, 1000);
            mTsView.setInAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_bottom));
            mTsView.setOutAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.slide_out_top));
            startFlipping();
        }
    }

    public void onResume() {
        startFlipping();
    }

    public void onStop() {
        stopFlipping();
    }
}
