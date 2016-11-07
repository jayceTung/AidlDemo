package com.asuper.aidldemo.okhttp;


import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by Super on 2016/11/7.
 */

public class OkDemo {

    public static void main(String[] args) {
        final Request request = new Request.Builder().url("http://www.baidu.com").build();
        System.out.println("req =" + request.toString());
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                System.out.println("request =" + request.toString());
            }

            @Override
            public void onResponse(Response response) throws IOException {
                System.out.println("response =" + response.toString());
            }
        });
    }
}
