package com.asuper.aidldemo.view;

import android.content.Context;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.asuper.aidldemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author super
 * @date 2017/12/15
 */
public class SelectLayout extends LinearLayout
        implements View.OnClickListener{
    private static final String TAG = "SelectLayout";

    private List<TextView> mTvList = new ArrayList<>();
    private Context mContext;
    private boolean isSelect;
    private boolean isDisable;

    public SelectLayout(Context context) {
        this(context, null);
    }

    public SelectLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SelectLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        isSelect = false;
        isDisable = false;
        initView();
    }

    private void initView() {
        setOrientation(LinearLayout.VERTICAL);
    }

    public void appendData(List<String> options) {
        if (options == null || options.isEmpty()) {
            return;
        }
        if (mTvList == null) {
            mTvList = new ArrayList<>();
        }
        mTvList.clear();
        for (int i = 0; i < options.size(); i++) {
            String option = options.get(i);

            TextView tvOption = new TextView(mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                    dip2px(60));
            params.topMargin = dip2px(10);
            params.gravity = Gravity.CENTER_VERTICAL;
            tvOption.setBackgroundResource(R.drawable.kk_bg_select_normal);
            tvOption.setPadding(dip2px(20), 0, 0, 0);
            tvOption.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            tvOption.setTextColor(mContext.getResources().getColor(R.color.kk_666666));
            tvOption.setGravity(Gravity.CENTER_VERTICAL);
            tvOption.setOnClickListener(this);
            setText(i, tvOption, option);
            mTvList.add(tvOption);
            addView(tvOption, params);
        }
    }

    private void setText(int position, TextView textView, String content) {
        switch (position) {
            case 0:
                textView.setText("A" + "." + content);
                break;
            case 1:
                textView.setText("B" + "." + content);
                break;
            case 2:
                textView.setText("C" + "." + content);
                break;
            case 3:
                textView.setText("D" + "." + content);
                break;
            default:
                textView.setText("N" + "." + content);
                break;
        }
    }

    public void setDisable() {
        if (mTvList != null && !mTvList.isEmpty()) {
            for (TextView view : mTvList) {
                isDisable = true;
                view.setBackgroundResource(R.drawable.kk_bg_select_disenable);
            }
        }
    }


    @Override
    public void onClick(View v) {
        if (isSelect || isDisable) {
            return;
        } else {
            v.setBackgroundResource(R.drawable.kk_bg_select_pressed);
            isSelect = true;
        }
    }

    public int dip2px(float dip) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dip, getContext().getApplicationContext().getResources().getDisplayMetrics()
        );
    }
}
