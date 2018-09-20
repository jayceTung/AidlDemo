package com.asuper.aidldemo.view.refresh;

/**
 * @author super
 * @date 2018/9/19
 */
public interface PullToRefreshListener {
    /**
     * 刷新中
     * @param refreshLayout
     */
    void onRefresh(RefreshLayout refreshLayout);

    /**
     * 开始刷新
     */
    void onStartRefresh();
    /**
     * 刷新完成
     */
    void onFinishRefresh();

    void onMax();

    void onPullDown();

    void onRise();
}
