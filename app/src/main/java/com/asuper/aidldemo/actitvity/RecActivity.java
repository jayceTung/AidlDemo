package com.asuper.aidldemo.actitvity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.asuper.aidldemo.R;
import com.asuper.aidldemo.View.MemberListView;
import com.asuper.aidldemo.eventbus.MessageEvent;
import com.asuper.aidldemo.parse.Util;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Super on 2017/2/21.
 */

public class RecActivity extends BaseActivity {
    private static final String TAG = "RecActivity";

    private MemberListView mView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        Util.sysncIsDebug(this);
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_recycler);
        mView = (MemberListView) findViewById(R.id.id_room_list);
        List<Integer> mData = new ArrayList<Integer>(Arrays.asList(R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
                R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher));
        mView.addList(mData);
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new MessageEvent("welcome"));
            }
        });
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

    /**
     * 无论从那个线程发布的事件都会在UI线程中执行
     * ThreadMode.MAIN
     * @param event
     * 对应低版本的onEventMainThread方法
     */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onEventMain(MessageEvent event) {
        if(event != null){
            Toast.makeText(this, event.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

}
