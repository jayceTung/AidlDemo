package com.asuper.aidldemo.view;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.asuper.aidldemo.R;

import java.util.List;

/**
 * @author super
 * @date 2018-12-11
 */
public class LoopView extends LinearLayout {
    private static final int KEY_DELAY_TIME = 5 * 1000;

    private MarqueeTextView mTv1;
    private MarqueeTextView mTv2;
    private Handler handler;
    private boolean isShow = false;
    private int startY1, endY1, startY2, endY2;
    private Runnable runnable;
    private List<String> list;
    private int position = 0;
    private int offsetY = 100;
    private boolean hasPostRunnable = false;
    private RelativeLayout mLayout;
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
        initView();
    }

    private void initView() {
        mIvIcon = newImageView();

        mTv1 = newTvView();
        mTv2 = newTvView();
        mLayout = newRelativeLayout();
        addView(mIvIcon);
        addView(mLayout);

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                isShow = !isShow;
                if (position == list.size() - 1) {
                    position = 0;
                }

                if (isShow) {
                    mTv1.setText(list.get(position++));
                    mTv2.setText(list.get(position));
                } else {
                    mTv2.setText(list.get(position++));
                    mTv1.setText(list.get(position));
                }

                startY1 = isShow ? 0 : offsetY;
                endY1 = isShow ? -offsetY : 0;
                ObjectAnimator animator = ObjectAnimator.ofFloat(mTv1, "translationY", startY1, endY1).setDuration(500);
                animator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        if (startY1 == 0) {
                            mTv1.stopScroll();
                        }
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if (startY1 > 0) {
                            mTv1.startScroll();
                        }
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                animator.start();


                startY2 = isShow ? offsetY : 0;
                endY2 = isShow ? 0 : -offsetY;
                ObjectAnimator animator2 = ObjectAnimator.ofFloat(mTv2, "translationY", startY2, endY2).setDuration(500);
                animator2.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        if (startY2 == 0) {
                            mTv2.stopScroll();
                        }
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if (startY2 > 0) {
                            mTv2.startScroll();
                        }
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                animator2.start();

                handler.postDelayed(runnable, KEY_DELAY_TIME);
            }
        };
    }

    private ImageView newImageView() {
        ImageView iv = new ImageView(getContext());
        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.CENTER_VERTICAL;
        iv.setImageResource(R.mipmap.bang_start_tip);
        iv.setLayoutParams(lp);
        return iv;
    }

    private RelativeLayout newRelativeLayout() {
        RelativeLayout rl = new RelativeLayout(getContext());
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER_VERTICAL;
        rl.setLayoutParams(params);
        rl.addView(mTv1);
        rl.addView(mTv2);
        return rl;
    }

    private MarqueeTextView newTvView() {
        MarqueeTextView mtv = new MarqueeTextView(getContext());
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.CENTER_VERTICAL;
        mtv.setLayoutParams(lp);
        mtv.setGravity(Gravity.CENTER_VERTICAL);
        return mtv;
    }

    public List<String> getList() {
        return list;
    }

    public void addData(List<String> list) {
        this.list = list;

        //处理最后一条数据切换到第一条数据 太快的问题
        if (list.size() > 1) {
            list.add(list.get(0));
        }
        startScroll();
    }

    public void startScroll() {
        mTv1.setText(list.get(0));
        if (list.size() > 1) {
            if(!hasPostRunnable) {
                hasPostRunnable = true;
                handler.post(runnable);
            }
        } else {
            //只有一条数据不进行滚动
            hasPostRunnable = false;
        }
    }

    public void stopScroll() {
        handler.removeCallbacks(runnable);
        hasPostRunnable = false;
    }
}
