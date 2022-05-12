package com.asuper.aidldemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import android.util.Log;

/**
 * Created by Joker on 2016/6/13.
 */
public class MyService extends Service {
    private static final String TAG = "MyService";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new IMyAidlInterfaceImpl();
    }

    private class IMyAidlInterfaceImpl extends IMyAidlInterface.Stub {

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public String printAndroidAidl(String name) throws RemoteException {
            Log.i(TAG, "name =" + name);
            return "print success";
        }
    }
}