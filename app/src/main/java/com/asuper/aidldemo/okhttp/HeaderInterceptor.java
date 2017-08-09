package com.asuper.aidldemo.okhttp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by super on 2017/8/4.
 */
public class HeaderInterceptor implements Interceptor {
    private static final String TAG = "HeaderInterceptor";

    private Map<String, String> header;

    public HeaderInterceptor() {
        header = new HashMap<>();
        header.put("OS", "android");
        header.put("Cache-Control", "private");
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();

        for(Map.Entry<String, String> entry : header.entrySet()) {
            builder.header(entry.getKey(), entry.getValue());
        }

        return chain.proceed(builder.build());
    }
}
