package com.asuper.aidldemo.okhttp;

import java.io.IOException;

import okhttp3.*;

/**
 * Created by Super on 2016/11/7.
 */

public class OkDemo {

    public static void main(String[] args) {
        final okhttp3.Request request = new okhttp3.Request.Builder()
                .url("www.baidu.com")
                .build();
        System.out.println("req =" + request.toString());
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }
}
