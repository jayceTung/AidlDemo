package com.asuper.aidldemo.okhttp;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by super on 2017/8/4.
 */

public class LoggerInterceptor implements Interceptor {
    private static final String TAG = "LoggerInterceptor";

    public LoggerInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        long t1 = System.nanoTime();
        Request request = chain.request();
        Log.d(TAG, String.format("send request %s \n connection %s \n header %s", request.url(),
                chain.connection(), request.headers()));
        Response response = chain.proceed(request);
        long t2 = System.nanoTime();
        Log.d(TAG, String.format("receive response %s \n on %.1fms%n", response.headers(), (t2 - t1)/ 1e6d));
        return response;
    }
}
