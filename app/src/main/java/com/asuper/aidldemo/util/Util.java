package com.asuper.aidldemo.util;

import android.content.Context;

/**
 * @author super
 * @date 11/17/20
 */
public class Util {
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
