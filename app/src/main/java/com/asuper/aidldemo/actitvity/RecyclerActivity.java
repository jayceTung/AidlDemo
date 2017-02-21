package com.asuper.aidldemo.actitvity;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
 * Created by Super on 2017/2/17.
 */

public class RecyclerActivity extends AppCompatActivity implements RoomGesturer.GestureListener {
    private RecyclerView mRecyclerView;
    private GalleryAdapter mAdapter;
    private List<Integer> mDatas;
    private Boolean isRight = true;
    private RoomGesturer mGesturer;
    private static final int FLING_MIN_DISTANCE = 50;
    private static final int FLING_MIN_VELOCITY = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        mGesturer = new RoomGesturer(this, this);
        initDatas();
        //得到控件
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview_horizontal);
        mRecyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mGesturer.getGestureDetector().onTouchEvent(event);
                return false;
            }
        });
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        //设置适配器
        mAdapter = new GalleryAdapter(this);

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.space);
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
    }

    public void onBtn(View v) {
        mAdapter.addDatas(mDatas);
    }

    public void onHide(View v) {
        ObjectAnimator.ofFloat(mRecyclerView, "translationY", 0f, 700f).setDuration(300).start();
    }


    private void initDatas() {

    }

    @Override
    public boolean onSingleTapUp() {
        return false;
    }

    @Override
    public void onLeftFling() {
        isRight = true;
    }

    @Override
    public void onRightFling() {
        isRight = false;
    }

    @Override
    public void onTopFling() {

    }

    @Override
    public void onDownFling() {

    }


    public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {
        private LayoutInflater mInflater;
        private List<Integer> mDatas;

        public GalleryAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
            mDatas = new ArrayList<Integer>();
        }

        public void addDatas(List<Integer> datas) {
            isRight = true;
            mDatas.clear();
            mDatas.addAll(datas);
            notifyDataSetChanged();
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
            ViewHolder viewHolder = new ViewHolder(view);

            viewHolder.mImg = (ImageView) view
                    .findViewById(R.id.id_index_gallery_item_image);
            viewHolder.mView = view.findViewById(R.id.view);
            return viewHolder;
        }

        /**
         * 设置值
         */
        @Override
        public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
            Log.i("DMC", "onBindViewHolder");
            viewHolder.mImg.setImageResource(mDatas.get(i));
            // 先进场再向上抖一下
//            AnimatorSet animatorSet = new AnimatorSet();
//            ObjectAnimator moveAnimator =  ObjectAnimator.ofFloat(viewHolder.mView, "translationX", 0f);
//            moveAnimator.setInterpolator(new DecelerateAccelerateInterpolator());
//            moveAnimator.setDuration(500);
//
//            animatorSet.play(moveAnimator);
//            animatorSet.start();
            if (isRight) {
                ViewCompat.setTranslationX(viewHolder.itemView, mRecyclerView.getMeasuredWidth());
            } else {
                ViewCompat.setTranslationX(viewHolder.itemView, -mRecyclerView.getMeasuredWidth());
            }
            ViewCompat.animate(viewHolder.itemView)
                      .translationX(0)
                        .setDuration(500)
                        .setInterpolator(new DecelerateInterpolator(1.4f))
                        .setListener(null)
                        .setStartDelay(0)
                        .start();



            if (i < 3) {
                ObjectAnimator moveAnimator =  ObjectAnimator.ofFloat(viewHolder.mView, "translationY", 0f, -50f, 0f);
                moveAnimator.setInterpolator(new LinearInterpolator());
                moveAnimator.setDuration(100);
                moveAnimator.setStartDelay(800 + i * 80);
                moveAnimator.start();
            }
        }

    }
}
