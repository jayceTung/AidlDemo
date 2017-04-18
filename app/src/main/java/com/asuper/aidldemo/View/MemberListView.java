package com.asuper.aidldemo.View;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.asuper.aidldemo.R;
import com.asuper.aidldemo.parse.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Super on 2017/3/8.
 */
public class MemberListView extends LinearLayout {
    private RecyclerView mRecyclerView;
    private ImageView mImageView;
    private Context mContext;
    private List<Integer> mList = new ArrayList<Integer>();
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
    }

    private void initView() {
        setOrientation(LinearLayout.HORIZONTAL);
        mRecyclerView = new RecyclerView(mContext);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        params.weight = 1;
        addView(mRecyclerView, params);

        mImageView = new ImageView(mContext);
        LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(100, 60);
        imageParams.leftMargin = 10;
        addView(mImageView, imageParams);
        mImageView.setBackgroundResource(R.mipmap.face);
    }

    public void addList(List<Integer> list) {
        mAdapter.addList(list);
    }

}