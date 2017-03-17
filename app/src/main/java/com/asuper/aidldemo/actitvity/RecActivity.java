package com.asuper.aidldemo.actitvity;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.asuper.aidldemo.R;
import com.asuper.aidldemo.View.MemberListView;
import com.asuper.aidldemo.parse.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Super on 2017/2/21.
 */

public class RecActivity extends AppCompatActivity {
    private static final String TAG = "RecActivity";

    private MemberListView mView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        Util.sysncIsDebug(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        mView = (MemberListView) findViewById(R.id.id_room_list);
        List<Integer> mData = new ArrayList<Integer>(Arrays.asList(R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
                R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher));
        mView.addList(mData);
        Log.i(TAG, Util.isDebug.booleanValue() + "");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "OnResume");
    }
}
