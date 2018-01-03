package com.asuper.aidldemo.music;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.asuper.aidldemo.R;
import com.asuper.aidldemo.actitvity.BaseActivity;


/**
 * @author super
 * @date 2018/1/3
 */
public class MusicActivity extends BaseActivity {
    private static final String TAG = "MusicActivity";

    private Button mBtnPlay;
    private Activityrececier activityrececier;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        mBtnPlay = (Button) this.findViewById(R.id.btn_play);
        activityrececier = new Activityrececier();
        IntentFilter filter = new IntentFilter();
        filter.addAction("ACTION_UPDATE");
        registerReceiver(activityrececier, filter);
        Intent intent=new Intent(this, MusicService.class);
        startService(intent);
        mBtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("ACTION_BF");
                sendBroadcast(intent);
            }
        });
    }

    class Activityrececier extends BroadcastReceiver {

        @Override
        public void onReceive(Context arg0, Intent intent) {

            int flag=intent.getIntExtra("flag", 0);
            Log.v("Activityrececier flag",flag+"");
            if(flag==0)
            {
                mBtnPlay.setText("暂停");
            }
            else{
                mBtnPlay.setText("播放");
            }

        }

    }


}
