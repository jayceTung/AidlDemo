package com.asuper.aidldemo.loader;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

import dalvik.system.DexClassLoader;

/**
 * @author SuperM1n
 * @brief description
 * @date 2020-12-24
 */
public class LoadUtil {

    public static void loadClass(String apkPath, Context context) {
        try {
            //宿主elementdex
            Class<?> dexPathClass = Class.forName("dalvik.system.DexPathList");
            Field dexElementsField = dexPathClass.getDeclaredField("dexElements");
            dexElementsField.setAccessible(true);

            Class<?> classLoaderClass = Class.forName("dalvik.system.BaseDexClassLoader");
            Field pathListField = classLoaderClass.getDeclaredField("pathList");
            pathListField.setAccessible(true);

            Object pathListObj = pathListField.get(context.getClassLoader());
            Object[] hostDexElements = (Object[]) dexElementsField.get(pathListObj);

            //插件dexElements
            DexClassLoader dexClassLoader = new DexClassLoader(apkPath, context.getCacheDir().getAbsolutePath(),
                    null, context.getClassLoader());
            Object pluginListObj = pathListField.get(dexClassLoader);
            Object[] pluginDexElements = (Object[]) dexElementsField.get(pluginListObj);

            //合并
            Object[] newElements = (Object[]) Array.newInstance(hostDexElements.getClass().getComponentType(), hostDexElements.length + pluginDexElements.length);
            System.arraycopy(hostDexElements, 0, newElements, 0, hostDexElements.length);
            System.arraycopy(pluginDexElements, 0, newElements, hostDexElements.length, hostDexElements.length + pluginDexElements.length);

            //赋值到宿主中去
            dexElementsField.set(hostDexElements, newElements);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
