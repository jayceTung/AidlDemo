package com.asuper.aidldemo.gson;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.lang.reflect.Type;

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

    public static Gson getGson() {
        return sGson;
    }

    private GsonUtil() {
    }

    /**
     * json 转换成 bean
     *
     * @param gsonString
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T GsonToBean(String gsonString, Class<T> clazz) {
        return sGson.fromJson(gsonString, clazz);
    }

    /**
     *
     * @param element
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T GsonToBean(JsonElement element, Class<T> clazz) {
        return sGson.fromJson(element, clazz);
    }

    public static <T> T GsonToBean(String gsonString, Type type) {
        return sGson.fromJson(gsonString, type);
    }

    /**
     * bean 转换成 json
     *
     * @param object
     * @return
     */
    public static String BeanToGson(Object object) {
        return sGson.toJson(object);
    }
}