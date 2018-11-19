package com.asuper.aidldemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.asuper.aidldemo.R;

/**
 * @author super
 * @date 2018/11/19
 */
public class LoadingAvatarView extends RelativeLayout {
    private Context mContext;

    private ImageView mIvWear;
    private ImageView mIvAvatar;

    private float mAvatarWidth;

    public LoadingAvatarView(Context context) {
        this(context, null);
    }

    public LoadingAvatarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingAvatarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mContext = context;
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.LoadingAvatarView);
        mAvatarWidth = ta.getDimension(R.styleable.LoadingAvatarView_avatar_width, 0);
        ta.recycle();

        mIvWear = new ImageView(context);
        mIvWear.setScaleType(ImageView.ScaleType.CENTER_CROP);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        addView(mIvWear, params);

        mIvAvatar = new ImageView(context);
        mIvAvatar.setScaleType(ImageView.ScaleType.CENTER_CROP);
        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(dp2px(context, mAvatarWidth),
                dp2px(context, mAvatarWidth));
        params1.addRule(RelativeLayout.CENTER_IN_PARENT);
        //index -1 默认顶层
        addView(mIvAvatar, 0, params1);
    }

    public void setImage() {
        mIvAvatar.setImageResource(R.mipmap.face);
        mIvWear.setImageResource(R.mipmap.bang_start_tip);
    }

    private int dp2px(Context context, float dp) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics()
        );
    }
}
