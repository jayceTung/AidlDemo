package com.asuper.aidldemo.view;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.asuper.aidldemo.R;

/**
 * @author super
 * @date 2018/10/18
 */
public class DrawView extends FrameLayout implements View.OnClickListener {
    Context mContext;
    LinearLayout mFlCardBack;
    LinearLayout mFlCardFront;
    FrameLayout mFlContainer;

    AnimatorSet mFrontSet;
    AnimatorSet mBackSet;
    private boolean mFlag = true;

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_flipcardsview,  this);
        mFlContainer = (FrameLayout) findViewById(R.id.main_fl_container);
        mFlCardBack = (LinearLayout) findViewById(R.id.main_fl_card_back);
        mFlCardFront = (LinearLayout) findViewById(R.id.main_fl_card_front);
        mFlCardBack.setAlpha(0);
        setOnClickListener(this);
    }

    private void setCameraDistance() {
        int distance = 16000;
        float scale = getResources().getDisplayMetrics().density * distance;
        mFlCardFront.setCameraDistance(scale);
        mFlCardBack.setCameraDistance(scale);
    }

    private void showAnimators() {
        mBackSet = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, R.animator.anim_in);
        mFrontSet = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, R.animator.anim_out);
    }

    private void hideAnimators() {
        mFrontSet = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, R.animator.anim_in);
        mBackSet = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, R.animator.anim_out);
    }

    public void start() {
        if (mFlag) {
            show();
        } else {
            hide();
        }
    }

    private void show() {
        showAnimators();
        setCameraDistance();
        mFrontSet.setTarget(mFlCardFront);
        mBackSet.setTarget(mFlCardBack);
        mFrontSet.start();
        mBackSet.start();
        mFlag = false;
    }

    private void hide() {
        hideAnimators();
        setCameraDistance();
        mFrontSet.setTarget(mFlCardFront);
        mBackSet.setTarget(mFlCardBack);
        mFrontSet.start();
        mBackSet.start();
        mFlag = true;
    }

    @Override
    public void onClick(View v) {
        start();
    }
}
