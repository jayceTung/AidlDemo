package com.asuper.aidldemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.asuper.aidldemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author super
 * @date 2018-12-04
 */
public class LoopNewsView extends LinearLayout {
    private static final String TAG = "LoopNewsView";
    private static final int ANIM_DELAYED_MILLIONS = 5 * 1000;
    private static final int ANIM_DURATION = 1 * 1000;

    private Drawable mTipIcon;
    private int mTextColor;
    private int mTextSize;

    private long lastTimeMillis;
    private int curTipIndex = 0;
    private ArrayList<String> mList;

    private TextView mTvIn;
    private TextView mTvOut;
    private ImageView mIcon;
    private FrameLayout mLayout;
    private Animation mAnimIn, mAnimOut;

    public void addData(List<String> list) {
        mList.clear();
        mList.addAll(list);
        start(0);
    }

    private void start(int position) {
        mAnimIn.cancel();
        mAnimOut.cancel();
        curTipIndex = position;
        setTip(mTvOut);
        updatePlayAnimation();
    }

    public LoopNewsView(@NonNull Context context) {
        this(context, null);
    }

    public LoopNewsView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoopNewsView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(HORIZONTAL);
        parseAttrs(context, attrs);
        initTipFrame();
        initAnimation();
    }

    private void parseAttrs(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.LoopNewsView);

        if (ta == null) return;

        for (int i = 0; i < ta.getIndexCount(); i++) {
            int attr = ta.getIndex(i);
            if (attr == R.styleable.LoopNewsView_lnv_tip_icon) {
                mTipIcon = ta.getDrawable(R.styleable.LoopNewsView_lnv_tip_icon);
            } else if (attr == R.styleable.LoopNewsView_lnv_text_color) {
                mTextColor = ta.getColor(R.styleable.LoopNewsView_lnv_text_color, 0);
            } else if (attr == R.styleable.LoopNewsView_lnv_text_size) {
                mTextSize = ta.getDimensionPixelOffset(R.styleable.LoopNewsView_lnv_text_size, 18);
            }
        }
        ta.recycle();
    }

    private void initTipFrame() {
        mList = new ArrayList<>();
        mIcon = newImageView();
        addView(mIcon);

        mTvIn = newTextView();
        mTvOut = newTextView();
        addChildView(mTvIn);
        addChildView(mTvOut);
    }

    private void addChildView(View view) {
        if (mLayout == null) {
            mLayout = new FrameLayout(getContext());
            LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            mLayout.setLayoutParams(params);
            addView(mLayout);
        }
        mLayout.addView(view);
    }

    private void initAnimation() {
        mAnimOut = newAnimation(0, -1);
        mAnimIn = newAnimation(1, 0);
        mAnimIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (curTipIndex % 2 == 0) {
                    mTvOut.setFocusableInTouchMode(true);
                    mTvOut.setFocusable(true);
                    mTvOut.setSingleLine(true);
                    mTvOut.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                } else {
                    mTvIn.setFocusableInTouchMode(true);
                    mTvIn.setFocusable(true);
                    mTvIn.setSingleLine(true);
                    mTvIn.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                }
                updateTip();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void updateTip() {
        if (System.currentTimeMillis() - lastTimeMillis < 1000) return;

        lastTimeMillis = System.currentTimeMillis();
        updatePlayAnimation();
    }

    private void setTip(TextView tv) {
        tv.setText(getNextTip());
    }

    private void updatePlayAnimation() {
        if (curTipIndex % 2 == 0) {
            setTip(mTvOut);
            mTvIn.startAnimation(mAnimOut);
            mTvOut.startAnimation(mAnimIn);
            this.bringChildToFront(mTvIn);
        } else {
            setTip(mTvIn);
            mTvOut.startAnimation(mAnimOut);
            mTvIn.startAnimation(mAnimIn);
            this.bringChildToFront(mTvOut);
        }
    }


    private TextView newTextView() {
        int marginLeft = 0;
        if (mIcon != null) {
            marginLeft = mIcon.getMeasuredWidth();
            Log.i(TAG, "marginLeft = " + marginLeft);
        }
        TextView tv = new TextView(getContext());
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        lp.setMargins(marginLeft, 0, 0, 0);
        tv.setLayoutParams(lp);
        tv.setCompoundDrawablePadding(10);
        tv.setGravity(Gravity.CENTER_VERTICAL);
        tv.setSingleLine(true);
        tv.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        tv.setHorizontallyScrolling(true);
        tv.setTextColor(mTextColor);
        tv.setTextSize(mTextSize);
        return tv;
    }

    private ImageView newImageView() {
        ImageView iv = new ImageView(getContext());
        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.CENTER_VERTICAL;
        iv.setImageDrawable(mTipIcon);
        iv.setLayoutParams(lp);
        return iv;
    }

    private Animation newAnimation(float fromValue, float toValue) {
        Animation anim = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, fromValue, Animation.RELATIVE_TO_SELF, toValue);
        anim.setDuration(ANIM_DURATION);
        anim.setStartOffset(ANIM_DELAYED_MILLIONS);
        anim.setInterpolator(new DecelerateInterpolator());
        return anim;
    }

    private String getNextTip() {
        if (mList == null || mList.isEmpty()) return null;

        return mList.get(curTipIndex++ % mList.size());
    }
}
