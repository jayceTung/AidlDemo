package com.asuper.aidldemo.actitvity;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by Super on 2017/2/20.
 */

public class AnimCome extends DefaultItemAnimator {
    @Override
    public boolean animateAdd(RecyclerView.ViewHolder holder) {
        ViewCompat.setTranslationX(holder.itemView, holder.itemView.getRootView().getWidth());
        ViewCompat.animate(holder.itemView)
                .translationX(0)
                .setDuration(500)
                .setInterpolator(new DecelerateInterpolator())
                .setListener(null)
                .setStartDelay(0)
                .start();
        return super.animateAdd(holder);
    }
}
