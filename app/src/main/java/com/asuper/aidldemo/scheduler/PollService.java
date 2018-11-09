package com.asuper.aidldemo.scheduler;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Build;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import com.asuper.aidldemo.actitvity.RecActivity;

/**
 * @author super
 * @date 2018/11/9
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class PollService extends JobService {
    private static final String TAG = "PollService";

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.i(TAG, "onStartJon");
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.i(TAG, "onStopJob");
        return false;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Messenger messenger = intent.getParcelableExtra("message");
        Message message = Message.obtain();
        message.what = RecActivity.MSG_MES;
        try {
            messenger.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return super.onStartCommand(intent, flags, startId);
    }
}
