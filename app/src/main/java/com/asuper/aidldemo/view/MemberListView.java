package com.asuper.aidldemo.view;

import android.content.Context;
import androidx.annotation.Nullable;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.asuper.aidldemo.R;

import java.util.List;

/**
 * Created by Super on 2017/3/8.
 */
public class MemberListView extends LinearLayout {
    private static final String TAG = "MemberListView";

    private RecyclerView mRecyclerView;
    private ImageView mImageView;
    private Context mContext;
    private MemberAdapter mAdapter;

    public MemberListView(Context context) {
        this(context, null);
    }

    public MemberListView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MemberListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
        initEvent();
    }

    private void initEvent() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new MemberAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setViewCacheExtension(new MyViewCacheExtension());
        mRecyclerView.setViewCacheExtension(new MyViewCacheExtension());
    }

    private void initView() {
        setOrientation(LinearLayout.HORIZONTAL);
        mRecyclerView = new RecyclerView(mContext);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        params.weight = 1;
        addView(mRecyclerView, params);
        mRecyclerView.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener());

        mImageView = new ImageView(mContext);
        LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(100, 60);
        imageParams.leftMargin = 10;
        addView(mImageView, imageParams);
        mImageView.setBackgroundResource(R.mipmap.face);
    }

    public void addList(List<Integer> list) {
        mAdapter.addList(list);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "dispatchTouchEvent");
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    class MyViewCacheExtension extends RecyclerView.ViewCacheExtension {

        @Override
        public View getViewForPositionAndType(RecyclerView.Recycler recycler, int position, int type) {
//            return viewType == DemoAdapter.TYPE_SPECIAL ? mAdapter.caches.get(position) : null;
            return null;
        }
    }
}
