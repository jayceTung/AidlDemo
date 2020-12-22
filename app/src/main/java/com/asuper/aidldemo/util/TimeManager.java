package com.asuper.aidldemo.util;

import android.os.SystemClock;

/**
 * @author SuperM1n
 * @brief description
 * @date 2020-12-21
 */
public class TimeManager {
    /** 从服务器获取时间戳 */
    private long currentTimeMillis;
    /** 开机后到当前时间间隔 */
    private long elapsedRealtime;

    public void setCurrentTimeMillis(long currentTimeMillis) {
        this.currentTimeMillis = currentTimeMillis;
        setElapsedRealtime(SystemClock.elapsedRealtime());
    }

    public void setElapsedRealtime(long elapsedRealtime) {
        this.elapsedRealtime = elapsedRealtime;
    }

    public long getCurrentTimeMillis() {
        long dis = SystemClock.elapsedRealtime() - elapsedRealtime;
        return currentTimeMillis + dis;
    }
}
