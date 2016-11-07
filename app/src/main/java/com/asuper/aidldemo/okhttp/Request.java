package com.asuper.aidldemo.okhttp;

import android.util.Log;

/**
 * Created by Super on 2016/11/7.
 */

public class Request {
    private static final String TAG = "Request";

    private String name;
    private String address;

    public Request(Builder builder) {
        name = builder.name;
        address = builder.address;
    }

    public static class Builder {
        private String name;
        private String address;

        public Builder() {
            this.name = "李三";
            this.address = "level";
        }

        public Builder addName(String name) {
            this.name = name;
            return this;
        }

        public Request build() {
            Log.i(TAG, "build");
            return new Request(this);
        }
    }
}
