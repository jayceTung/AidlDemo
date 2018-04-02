package com.asuper.aidldemo.gson;

import com.google.gson.Gson;

/**
 * @author super
 * @date 2018/4/2
 */
public class GsonUtil {
    private static Gson sGson = null;

    static {
        if (sGson == null) {
            sGson = new Gson();
        }
    }

    private static Gson getGson() {
        return sGson;
    }

    private GsonUtil() {
    }
}
