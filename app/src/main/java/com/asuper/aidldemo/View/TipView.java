package com.asuper.aidldemo.View;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.asuper.aidldemo.R;


/**
 * Created by Super on 2016/9/22.
 */
public class TipView extends RelativeLayout {
    private static final String TAG = "TipView";

    private Context mContext;
    private ImageView mImageView;
    private float pointY;

    public TipView(Context context) {
        this(context, null);
    }

    public TipView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TipView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mImageView = new ImageView(mContext);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mImageView.setLayoutParams(params);
        addView(mImageView);
    }

    public void start() {
        mImageView.setVisibility(View.VISIBLE);
        mImageView.setImageResource(R.mipmap.kk_plugin_screen_red_dot);

        ObjectAnimator anim = ObjectAnimator
                .ofFloat(mImageView, "zhy", 0F, 1.5F);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                float cVal = (Float) animation.getAnimatedValue();
                Log.e(TAG, "cVal =" + cVal);
                mImageView.setAlpha(cVal);
                mImageView.setScaleX((float) (cVal * 1.5));
                mImageView.setScaleY((float) (cVal * 1.5));
                mImageView.setX((float) (cVal * 180));
                mImageView.setY((float) (cVal * cVal * 0.5 * 400));
            }
        });
        anim.setDuration(500);


        ObjectAnimator animator = ObjectAnimator.ofFloat(mImageView, "DMC", 0.5f, 1.0f);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                if (value == 0.5) {
                    mImageView.setImageResource(R.mipmap.bang_start_tip);
                }
                Log.e(TAG, "value =" + value);
                if (value <= 0.6) {
                    mImageView.setY((value + 1) * (value + 1) * 200);
                    mImageView.setScaleX((float) (value + 0.4));
                    mImageView.setScaleY((float) (value + 0.4));
                }

            }
        });
        anim.setDuration(1000);

        pointY = (float) (1.6 * 1.6 * 200);
        ObjectAnimator ani = ObjectAnimator.ofFloat(mImageView, "translationY", pointY, pointY - 80);
        ani.setDuration(100);


        ObjectAnimator repeatAnim = ObjectAnimator.ofFloat(mImageView, "translationY", pointY - 80, pointY - 40);
        repeatAnim.setRepeatCount(3);
        repeatAnim.setRepeatMode(ValueAnimator.REVERSE);
        repeatAnim.setDuration(1000);

        ObjectAnimator moveDown = ObjectAnimator.ofFloat(mImageView, "translationY", pointY - 80, pointY);
        moveDown.setDuration(100);

        ObjectAnimator dismiss = ObjectAnimator.ofFloat(mImageView, "DMC", 1.0f, 0);
        dismiss.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                mImageView.setY((float) ((value * 100)));
                mImageView.setScaleX((float) (value));
                mImageView.setScaleY((float) (value));
                mImageView.setAlpha(value);
            }
        });
        dismiss.setDuration(500);

        AnimatorSet set = new AnimatorSet();
        set.playSequentially(anim, animator, ani, repeatAnim, moveDown, getAnimationSet());
        set.setInterpolator(new LinearInterpolator());
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mImageView.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        set.start();

    }

    private AnimatorSet getAnimationSet() {
        ObjectAnimator scaleXAnim = ObjectAnimator.ofFloat(mImageView, "scaleX", 1f, 0.3f);
        ObjectAnimator scaleYAnim = ObjectAnimator.ofFloat(mImageView, "scaleY", 1f, 0.3f);
        ObjectAnimator tranYAnim = ObjectAnimator.ofFloat(mImageView, "translationY", pointY, pointY - 120);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(scaleXAnim, scaleYAnim, tranYAnim);
        set.setDuration(500);
        return set;
    }

    public void open() {
        mImageView.setImageResource(R.mipmap.bang_start_tip);
        ObjectAnimator animator = ObjectAnimator.ofFloat(mImageView, "DMC", 0.1f, 1.0f);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                Log.e(TAG, "value =" + value);
                mImageView.setScaleX(value);
                mImageView.setScaleY(value);
                if (value <= 0.5) {
                    mImageView.setY((float) ((value + 1.4) * (value + 1.4) * 200));
                }

            }
        });
        animator.setDuration(500);
        animator.start();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        LinearGradient linearShader = new LinearGradient(0, 0, 100, 100,
                new int[]{Color.RED, Color.BLACK, Color.BLUE, Color.DKGRAY},
                null, Shader.TileMode.CLAMP);
        paint.setShader(linearShader);
        canvas.drawCircle(0, 0, 10, paint);
    }
}
