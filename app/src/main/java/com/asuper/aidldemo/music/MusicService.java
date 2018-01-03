package com.asuper.aidldemo.music;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

import com.asuper.aidldemo.R;

/**
 * @author super
 * @date 2018/1/3
 */
public class MusicService extends Service {
    // 用于创建通知栏布局
    private RemoteViews remoteviews;

//    private MediaPlayer mplayer;

    private NotificationManager nm;
    private Notification nf;

    // 广播监听的动作
    public static final String ACTION_BF = "ACTION_BF";

    private int flag = 1; // 记录当前播放状态

    private MusicServiceReceiver mr;

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.i("MusicService", "onCreate");
        // 创建notification
        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // 注册receiver
        mr = new MusicServiceReceiver();
        IntentFilter intentfilter = new IntentFilter();
        intentfilter.addAction(ACTION_BF);

        registerReceiver(mr, intentfilter);


        //创建notification
        nf = new Notification(R.mipmap.ic_launcher, "月牙湾",
                System.currentTimeMillis());

        remoteviews = new RemoteViews(this.getPackageName(),
                R.layout.play_notify_big_view);
        Intent intent1 = new Intent();
        intent1.setAction(ACTION_BF);
        intent1.putExtra("flag", 0);

        PendingIntent contentIntent = PendingIntent.getBroadcast(this, 0,
                intent1, 0);
        remoteviews.setOnClickPendingIntent(R.id.play_notify_play, contentIntent);

        nf.contentView = remoteviews;
        nm.notify(0, nf);

        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(mr);
//        mplayer.stop();
//        mplayer.release();
        super.onDestroy();
    }

    class MusicServiceReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context arg0, Intent arg1) {

            flag = (flag == 1 ? 0 : 1);
            Log.v("music receiver", "receiver" + flag);
            if (flag == 0) // 正在播放
            {
//                mplayer.start();

                nf.contentView.setImageViewResource(R.id.play_notify_play, R.mipmap.ic_pause);
            }
            if (flag == 1) //
            {
//                mplayer.pause();

                nf.contentView.setImageViewResource(R.id.play_notify_play, R.mipmap.ic_play_arrow);

            }

            Intent intent = new Intent("ACTION_UPDATE");
            intent.putExtra("flag", flag);
            // 发送广播，将被Activity组件中的BroadcastReceiver接收到
            sendBroadcast(intent);
            nm.notify(0, nf);

        }

    }
}
