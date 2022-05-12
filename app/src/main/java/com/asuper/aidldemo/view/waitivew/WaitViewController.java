package com.asuper.aidldemo.view.waitivew;

import android.content.Context;
import android.graphics.Color;
import android.graphics.RectF;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.ColorInt;
import androidx.annotation.Dimension;
import androidx.annotation.IntRange;

import com.asuper.aidldemo.R;

/**
 * @author super
 * @date 2018/10/8
 */
public class WaitViewController {
    private static final OnWaitViewFilter DEFAULT_WAITVIEW_FILTER = new SimpleOnWaitViewFilter();

    private final View mWrapperView;
    private OnWaitViewFilter mOnWaitViewFilter = DEFAULT_WAITVIEW_FILTER;

    @ColorInt
    private int mColor = Color.parseColor("#E9E9E9");
    @Dimension
    private int mRadius;
    @IntRange(from=0, to=255) private int mAlpha = 255;
    private RectF mDrawRect;

    private WaitView mWaitView;

    private WaitViewController(View view, RectF drawRect) {
        this.mWrapperView = view;
        this.mDrawRect = drawRect;
        mRadius = dp2px(view.getContext(), 4);
    }

    public static WaitViewController from(View view) {
        if (view instanceof WaitView) {
            return ((WaitView) view).getController();
        }

        int width = view.getWidth();
        int height = view.getHeight();
        if (width == 0) {
            int spec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            view.measure(spec, spec);
            width = view.getMeasuredWidth();
            height = view.getMeasuredHeight();
        }

        RectF drawRect = new RectF(0, 0, width-view.getPaddingLeft()-view.getPaddingRight(),
                height-view.getPaddingTop()-view.getPaddingBottom());
        WaitViewController controller = (WaitViewController) view.getTag(R.id.root_view);
        if (controller == null) {
            controller = new WaitViewController(view, drawRect);
            view.setTag(R.id.root_view, controller);
        }
        return controller;
    }


    public View render() {
        if (mWaitView == null) {
            ViewGroup parent = (ViewGroup) mWrapperView.getParent();
            mWaitView = new WaitView(this);
            mWaitView.setId(mWrapperView.getId());
            mWaitView.color(mColor).radius(mRadius).alpha(mAlpha).drawRect(mDrawRect);
            ViewGroup.LayoutParams params = mWrapperView.getLayoutParams();
            int index = parent.indexOfChild(mWrapperView);
            parent.removeView(mWrapperView);
            parent.addView(mWaitView, index, params);
        } else {
            mWaitView.color(mColor).radius(mRadius).alpha(mAlpha).drawRect(mDrawRect);
        }
        return mWaitView;
    }
    public void renderChilds() {
        render(mWrapperView);
    }
    private void render(View view) {
        FilterType filterType = mOnWaitViewFilter.onFilter(view);
        switch (filterType) {
            case Ignore:
                break;
            case Children:
                if (view instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) view;
                    final int count = viewGroup.getChildCount();
                    for (int i = 0; i < count; i++) {
                        View childView = viewGroup.getChildAt(i);
                        render(childView);
                    }
                }
                break;
            case WaitView:
                WaitViewController.from(view).render();
                break;
        }
    }

    public void remove() {
        if (mWaitView == null) {
            return;
        }

        ViewGroup parent = (ViewGroup) mWaitView.getParent();
        ViewGroup.LayoutParams params = mWaitView.getLayoutParams();
        int index = parent.indexOfChild(mWaitView);
        parent.removeView(mWaitView);
        parent.addView(mWrapperView, index, params);
        mWaitView = null;
    }
    public void removeChilds() {
        remove(mWrapperView);
    }
    private void remove(View view) {
        FilterType filterType = mOnWaitViewFilter.onFilter(view);
        switch (filterType) {
            case Ignore:
                break;
            case Children:
                if (view instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) view;
                    final int count = viewGroup.getChildCount();
                    for (int i = 0; i < count; i++) {
                        View childView = viewGroup.getChildAt(i);
                        remove(childView);
                    }
                }
                break;

            case WaitView:
                WaitViewController.from(view).remove();
                break;
        }
    }

    public WaitViewController color(@ColorInt int color) {
        mColor = color;
        return this;
    }
    public WaitViewController radius(@Dimension int radius) {
        mRadius = radius;
        return this;
    }
    public WaitViewController alpha(@IntRange(from=0, to=255) int alpha) {
        mAlpha = alpha;
        return this;
    }
    public WaitViewController drawRect(RectF drawRect) {
        mDrawRect = drawRect;
        return this;
    }
    public WaitViewController drawRect(int width, int height) {
        mDrawRect = new RectF(0, 0, width, height);
        return this;
    }
    public WaitViewController filter(OnWaitViewFilter filter) {
        mOnWaitViewFilter = filter;
        return this;
    }

    Context getContext() {
        return mWrapperView.getContext();
    }

    private static int dp2px(Context context, float dp) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics()
        );
    }

}
