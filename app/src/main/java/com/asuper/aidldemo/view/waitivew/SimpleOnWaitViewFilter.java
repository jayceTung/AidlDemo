package com.asuper.aidldemo.view.waitivew;

import android.view.View;
import android.view.ViewGroup;

/**
 * @author super
 * @date 2018/10/8
 */
public class SimpleOnWaitViewFilter implements OnWaitViewFilter {
    @Override
    public FilterType onFilter(View view) {
        if (view == null) {
            return FilterType.Ignore;
        }

        //过滤不可见的
        if (view.getVisibility() != View.VISIBLE) {
            return FilterType.Ignore;
        }

        if (View.class.equals(view.getClass())) {
            return FilterType.Ignore;
        }

        if (view instanceof ViewGroup) {
            return FilterType.Children;
        } else {
            return FilterType.WaitView;
        }
    }
}
