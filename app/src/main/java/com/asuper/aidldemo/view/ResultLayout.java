package com.asuper.aidldemo.view;

import android.content.Context;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.asuper.aidldemo.R;

import java.util.List;

/**
 * @author super
 * @date 2017/12/19
 */
public class ResultLayout extends LinearLayout {
    private static final String TAG = "ResultLayout";
    private static final int PROGRESS_MAX = 100;

    private Context mContext;

    public ResultLayout(Context context) {
        this(context, null);
    }

    public ResultLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ResultLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView() {
        setOrientation(LinearLayout.VERTICAL);
    }

    public void appendData(List<String> options) {
        if (options == null || options.isEmpty()) {
            return;
        }
        for (int i = 0; i < options.size(); i++) {
            String option = options.get(i);

            RelativeLayout rlLayout = new RelativeLayout(mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                    dip2px(60));
            params.topMargin = dip2px(10);
            rlLayout.setBackgroundResource(R.drawable.kk_bg_select_normal);

            ProgressBar progressBar = new ProgressBar(mContext, null, android.R.attr.progressBarStyleHorizontal);
            RelativeLayout.LayoutParams paramsBar = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT);
            progressBar.setLayoutParams(paramsBar);
            progressBar.setProgressDrawable(mContext.getResources().getDrawable(R.drawable.progress_bar_drawable));
            progressBar.setMax(PROGRESS_MAX);
            // TODO: 2017/12/19 判断是否百分百
            // TODO: 2017/12/19 setProgress
            progressBar.setProgress(40);
            rlLayout.addView(progressBar);

            LinearLayout linearLayout = new LinearLayout(mContext);
            RelativeLayout.LayoutParams lpLinear = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);

            TextView tvContent = new TextView(mContext);
            LinearLayout.LayoutParams lpContent = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.MATCH_PARENT);
            lpContent.leftMargin = dip2px(20);
            lpContent.weight = 1;
            tvContent.setGravity(Gravity.CENTER_VERTICAL);
            tvContent.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            tvContent.setTextColor(mContext.getResources().getColor(R.color.kk_333333));
            setText(i, tvContent, option);
            linearLayout.addView(tvContent, lpContent);

            TextView tvCount = new TextView(mContext);
            LinearLayout.LayoutParams lpCount = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.MATCH_PARENT);
            lpCount.leftMargin = dip2px(5);
            lpCount.rightMargin = dip2px(20);
            tvCount.setGravity(Gravity.CENTER_VERTICAL);
            tvContent.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            tvContent.setTextColor(mContext.getResources().getColor(R.color.kk_666666));
            tvCount.setText("11111");
            linearLayout.addView(tvCount, lpCount);

            rlLayout.addView(linearLayout, lpLinear);
            addView(rlLayout, params);
        }
    }

    private void setText(int position, TextView textView, String content) {
        switch (position) {
            case 0:
                textView.setText("A." + content);
                break;
            case 1:
                textView.setText("B." + content);
                break;
            case 2:
                textView.setText("C." + content);
                break;
            case 3:
                textView.setText("D." + content);
                break;
            default:
                textView.setText("N." + content);
                break;
        }
    }

    public int dip2px(float dip) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dip, getContext().getApplicationContext().getResources().getDisplayMetrics()
        );
    }
}
