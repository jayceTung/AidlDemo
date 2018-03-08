package com.asuper.aidldemo.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.asuper.aidldemo.R;

/**
 * @author super
 * @date 2018/3/8
 */
public class LayoutView extends LinearLayout {

    private TextView mTvText;
    private ImageView mIvIamge;

    public LayoutView(Context context) {
        this(context, null);
    }

    public LayoutView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LayoutView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.aidl_layout_view_activity, this);
        initView();
    }

    private void initView() {
        mTvText = (TextView) findViewById(R.id.tv_text);
        mIvIamge = (ImageView) findViewById(R.id.iv_image);
    }

    public void setData() {
        mTvText.setText(R.string.app_name);
        mIvIamge.setBackgroundResource(R.mipmap.face);
    }
}
