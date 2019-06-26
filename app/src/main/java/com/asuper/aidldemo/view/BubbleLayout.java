package com.asuper.aidldemo.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.asuper.aidldemo.R;

/**
 * @author super
 * @date 2019-06-25
 */
public class BubbleLayout extends LinearLayout {
    private TextView mTvContent;
    private TextView mTvDesc;
    private RelativeLayout mRlBg;

    public BubbleLayout(Context context) {
        this(context, null);
    }

    public BubbleLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BubbleLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
        initView();
    }

    public void setData(String string) {
        mTvDesc.setText(string);
        mTvContent.setText(string);
        mRlBg.setBackgroundResource(R.mipmap.bang_start_tip);
    }

    private void initView() {
        mTvContent = newTvView();
        mTvDesc = newTvDescView();
        mRlBg = newRelativeLayout();
        addView(mRlBg);
        addView(mTvDesc);
    }

    private TextView newTvView() {
        TextView tv = new TextView(getContext());
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.CENTER_IN_PARENT);
        tv.setLayoutParams(lp);
        tv.setGravity(Gravity.CENTER);
        tv.setTextColor(Color.parseColor("#4288FF"));
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        return tv;
    }

    private TextView newTvDescView() {
        TextView tv = new TextView(getContext());
        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.CENTER;
        tv.setLayoutParams(lp);
        tv.setGravity(Gravity.CENTER);
        tv.setTextColor(Color.parseColor("#FFFFFF"));
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
        return tv;
    }

    private RelativeLayout newRelativeLayout() {
        RelativeLayout rl = new RelativeLayout(getContext());
        LayoutParams params = new LayoutParams(dipToPx(50), dipToPx(50));
        rl.setLayoutParams(params);
        rl.addView(mTvContent);
        return rl;
    }

    /**
     * dp转换成像素
     *
     * @param dip
     * @return
     */
    private int dipToPx(int dip) {
        float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f * (dip >= 0 ? 1 : -1));
    }
}
