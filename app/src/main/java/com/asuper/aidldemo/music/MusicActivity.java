package com.asuper.aidldemo.music;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.asuper.aidldemo.R;
import com.asuper.aidldemo.actitvity.BaseActivity;


/**
 * @author super
 * @date 2018/1/3
 */
public class MusicActivity extends BaseActivity {
    private static final String TAG = "MusicActivity";

    private Button mBtnPlay;
    private TextView mTvMD5;
    private Activityrececier activityrececier;
    private PowerManager.WakeLock wakeLock;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        mBtnPlay = (Button) this.findViewById(R.id.btn_play);
        mTvMD5 = (TextView) this.findViewById(R.id.tv_md5);

        if (Build.VERSION.SDK_INT >= 23) {
            int REQUEST_CODE_CONTACT = 101;
            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            //验证是否许可权限
            for (String str : permissions) {
                if (this.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                    //申请权限
                    this.requestPermissions(permissions, REQUEST_CODE_CONTACT);
                }
            }
        }

        activityrececier = new Activityrececier();
        IntentFilter filter = new IntentFilter();
        filter.addAction("ACTION_UPDATE");
        registerReceiver(activityrececier, filter);
        Intent intent=new Intent(this, MusicService.class);
        startService(intent);
        mBtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvMD5.setText("");
                mTvMD5.setText(MD5Util.getMD5());
            }
        });

        wake();
    }

    private void wake() {
        PowerManager power = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = power.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, TAG);
        wakeLock.acquire();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (wakeLock != null) {
            wakeLock.release();
        }
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
