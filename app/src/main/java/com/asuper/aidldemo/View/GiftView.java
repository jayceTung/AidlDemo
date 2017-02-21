package com.asuper.aidldemo.View;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.asuper.aidldemo.R;
import com.asuper.aidldemo.actitvity.GalleryAdapter;
import com.asuper.aidldemo.actitvity.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Super on 2017/2/20.
 */

public class GiftView extends RelativeLayout {
    private RecyclerView mRecyclerView;
    private Context mContext;
    private GalleryAdapter mAdapter;
    private List<Integer> mData;
    private RecyclerTouchListener mListener;

    public GiftView(Context context) {
        this(context, null);
    }

    public GiftView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GiftView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    public void setListener(RecyclerTouchListener listener) {
        mListener = listener;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initView();
        initEvent();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview_horizontal);
    }

    private void initEvent() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        //设置适配器
        mAdapter = new GalleryAdapter(mContext, mRecyclerView.getMeasuredWidth());

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.space);
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mListener.recyclerOnTouch(event);
                return false;
            }
        });
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRecyclerView.isShown()) {
                    ObjectAnimator.ofFloat(mRecyclerView, "translationY", 0f, 700f).setDuration(300).start();
                    setVisibility(GONE);
                }
            }
        });
    }

    public void initData() {
        setVisibility(VISIBLE);
        mData = new ArrayList<Integer>(Arrays.asList(R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
                R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher));
        mAdapter.addDatas(mData);
    }

    public interface RecyclerTouchListener {
        void recyclerOnTouch(MotionEvent event);
    }

    public void setFling(Boolean right) {
        mAdapter.setFling(right);
    }
}
