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
    private Context mContext;
    private OnRefreshListener mListener;

    public void setListener(OnRefreshListener listener) {
        this.mListener = listener;
    }

    public SunshineRefresh(Context context) {
        this(context, null);
    }

    public SunshineRefresh(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SunshineRefresh(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initSunshine();
    }


    private void initSunshine() {
        setHeadHeight(dip2px(getContext(), 80));

        final View headerView = LayoutInflater.from(getContext()).inflate(R.layout.view_header, null);
        final UpdateView sunshineView  = (UpdateView) headerView.findViewById(R.id.update);
        addHeadView(headerView);

        setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                if (mListener != null) {
                    mListener.onRefresh();
                }
                sunshineView.startAnim();
                sunshineView.postDelayed(new Thread(){
                    @Override
                    public void run() {
                        finishRefreshing();
                        sunshineView.stopAnim();
                    }
                },1000);
            }

            @Override
            public void onStartRefresh() {
                Log.e("SunshineRefresh","onStartRefresh");
            }

            @Override
            public void onFinishRefresh() {
                Log.e("SunshineRefresh","onFinishRefresh");
            }

            @Override
            public void onMax() {
                sunshineView.setText(mContext.getString(R.string.kk_refreshing));
            }

            @Override
            public void onPullDown() {
                sunshineView.setText(mContext.getString(R.string.pull_to_refresh));
            }

            @Override
            public void onRise() {
                sunshineView.setText(mContext.getString(R.string.pull_to_refresh));
            }
        });
    }

    public interface OnRefreshListener {
        void onRefresh();
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
