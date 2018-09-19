package com.asuper.aidldemo.view.refresh;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.asuper.aidldemo.R;

/**
 * @author super
 * @date 2018/9/19
 */
public class SunshineRefresh extends RefreshLayout {
    public SunshineRefresh(Context context) {
        super(context);
        initSunshine();
    }

    public SunshineRefresh(Context context, AttributeSet attrs) {
        super(context, attrs);
        initSunshine();
    }

    public SunshineRefresh(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initSunshine();
    }


    private void initSunshine() {
        setHeadHeight(dip2px(getContext(), 80));

        final View headerView = LayoutInflater.from(getContext()).inflate(R.layout.view_header, null);
        final SunshineView sunshineView  = (SunshineView) headerView.findViewById(R.id.sunshine);
        addHeadView(headerView);

        setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                sunshineView.startAnim();
                sunshineView.postDelayed(new Thread(){
                    @Override
                    public void run() {
                        finishRefreshing();
                        sunshineView.stopAnim();
                    }
                },2000);
            }

            @Override
            public void onStartRefresh() {
                Log.e("SunshineRefresh","onStartRefresh");
            }

            @Override
            public void onFinishRefresh() {
                Log.e("SunshineRefresh","onFinishRefresh");
            }
        });

    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
