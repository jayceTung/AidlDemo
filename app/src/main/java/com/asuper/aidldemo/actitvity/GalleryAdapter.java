package com.asuper.aidldemo.actitvity;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.asuper.aidldemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Super on 2017/2/20.
 */

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private List<Integer> mDatas;
    private Boolean isRight = true;
    private int mViewWidth;

    public GalleryAdapter(Context context, int viewWidth) {
        mInflater = LayoutInflater.from(context);
        mDatas = new ArrayList<Integer>();
        mViewWidth = 800;
    }

    public void addDatas(List<Integer> datas) {
        isRight = true;
        mDatas.clear();
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    public void setFling(Boolean right) {
        isRight = right;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View arg0) {
            super(arg0);
        }

        View mView;
        ImageView mImg;
        TextView mTxt;
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.activity_recycler_item, parent, false);
        GalleryAdapter.ViewHolder viewHolder = new GalleryAdapter.ViewHolder(view);

        viewHolder.mImg = (ImageView) view
                .findViewById(R.id.id_index_gallery_item_image);
        viewHolder.mView = view.findViewById(R.id.view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.i("DMC", "onBindViewHolder");
        holder.mImg.setImageResource(mDatas.get(position));
        // 先进场再向上抖一下
//            AnimatorSet animatorSet = new AnimatorSet();
//            ObjectAnimator moveAnimator =  ObjectAnimator.ofFloat(viewHolder.mView, "translationX", 0f);
//            moveAnimator.setInterpolator(new DecelerateAccelerateInterpolator());
//            moveAnimator.setDuration(500);
//
//            animatorSet.play(moveAnimator);
//            animatorSet.start();
        if (isRight) {
            ViewCompat.setTranslationX(holder.itemView, mViewWidth);
        } else {
            ViewCompat.setTranslationX(holder.itemView, -mViewWidth);
        }
        ViewCompat.animate(holder.itemView)
                .translationX(0)
                .setDuration(500)
                .setInterpolator(new DecelerateInterpolator(1.4f))
                .setListener(null)
                .setStartDelay(0)
                .start();


        if (position < 3) {
            ObjectAnimator moveAnimator = ObjectAnimator.ofFloat(holder.mView, "translationY", 0f, -50f, 0f);
            moveAnimator.setInterpolator(new LinearInterpolator());
            moveAnimator.setDuration(100);
            moveAnimator.setStartDelay(800 + position * 80);
            moveAnimator.start();
        }
    }

}