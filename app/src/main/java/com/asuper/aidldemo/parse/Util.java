package com.asuper.aidldemo.parse;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.opengl.GLES20;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Super on 2016/10/19.
 */
public class Util {
    public static <T> T parseJsonToObject(Class<T> cls, JSONObject jsonObject) {
        T t = null;
        try {
            t = cls.newInstance();
            // 反射获取所有方法
            Method[] methods = cls.getDeclaredMethods();
            if (null != methods) {
                for (Method method : methods) {
                    String methodName = method.getName();
                    // 根据set方法对属性进行赋值，set方法符合set+属性名（首字母大写）的规范，自动生成即可
                    // 和struts把表单数据实例化的原理一致
                    if (methodName.startsWith("set")) {
                        // 获取属性名称
                        String attributeName = methodName.substring(3, 4).toLowerCase() + methodName.substring(4);

                        // 获取set方法中的参数类型，有且只有一个参数
                        Class<?> type = method.getParameterTypes()[0];
                        // 根据参数类型来对获取JSON中对应字段的值并进行赋值，主要用到了invoke方法
                        try {
                            // 基本类型
                            if (type == boolean.class || type == Boolean.class) {
                                method.invoke(t, new Object[] { jsonObject.getBoolean(attributeName) });
                            } else if (type == byte.class || type == Byte.class) {
                                method.invoke(t, new Object[] { jsonObject.getInt(attributeName) });
                            } else if (type == char.class || type == Character.class) {
                                method.invoke(t, new Object[] { jsonObject.getInt(attributeName) });
                            } else if (type == short.class || type == Short.class) {
                                method.invoke(t, new Object[] { jsonObject.getInt(attributeName) });
                            } else if (type == int.class || type == Integer.class) {
                                method.invoke(t, new Object[] { jsonObject.getInt(attributeName) });
                            } else if (type == long.class || type == Long.class) {
                                method.invoke(t, new Object[] { jsonObject.getLong(attributeName) });
                            } else if (type == float.class || type == Float.class) {
                                method.invoke(t, new Object[] { jsonObject.getDouble(attributeName) });
                            } else if (type == double.class || type == Double.class) {
                                method.invoke(t, new Object[] { jsonObject.getDouble(attributeName) });
                            } else if (type == String.class) {
                                method.invoke(t, new Object[] { jsonObject.getString(attributeName) });
                            } else if (List.class.isAssignableFrom(type)) {
                                // 集合类型
                                List<?> list = null;
                                if (list == null) {
                                    // 实例化list对象
                                    if (type.isInterface()) {
                                        list = new ArrayList();
                                    } else {
                                        list = (List<?>) type.newInstance();
                                    }

                                    // 获取Json数组
                                    JSONArray jsonArray = jsonObject.optJSONArray(attributeName);
                                    // 获取List的泛型类型
                                    Class<?> listType = (Class<?>) ((ParameterizedType) cls.getDeclaredField(
                                            attributeName).getGenericType()).getActualTypeArguments()[0];
                                    // 为List属性进行赋值
                                    decodeList(list, jsonArray, listType);
                                    method.invoke(t, new Object[] { list });
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 为List属性进行赋值
     *
     * @param list
     * @param jsonArray
     * @param type
     * @throws Exception
     */
    private static void decodeList(List list, JSONArray jsonArray, Class type) throws Exception {
        if (jsonArray == null) {
            return;
        }
        // 递归调用，把Json转为Java对象并添加到List中
        for (int i = 0; i < jsonArray.length(); i++) {
            list.add(parseJsonToObject(type, jsonArray.getJSONObject(i)));
        }
    }


    public static Boolean isDebug = null;


    public static boolean isDebug() {
        return isDebug == null ? false : isDebug.booleanValue();
    }

    public static void sysncIsDebug(Context context) {
        if (isDebug == null) {
            isDebug = context.getApplicationInfo() != null &&
                    (context.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        }
    }

    public static String readRawTextFile(Context ctx, int resId) {
        InputStream inputStream = ctx.getResources().openRawResource(resId);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i;
        try {
            i = inputStream.read();
            while (i != -1) {
                byteArrayOutputStream.write(i);
                i = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            return null;
        }
        return byteArrayOutputStream.toString();
    }

    /**
     * 创建shader程序的方法
     */
    public static int createProgram(String vertexSource, String fragmentSource) {
        //加载顶点着色器
        int vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, vertexSource);
        if (vertexShader == 0)
        {
            return 0;
        }

        //加载片元着色器
        int pixelShader = loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentSource);
        if (pixelShader == 0)
        {
            return 0;
        }

        //创建程序
        int program = GLES20.glCreateProgram();
        //若程序创建成功则向程序中加入顶点着色器与片元着色器
        if (program != 0)
        {
            //向程序中加入顶点着色器
            GLES20.glAttachShader(program, vertexShader);
            checkGlError("glAttachShader");
            //向程序中加入片元着色器
            GLES20.glAttachShader(program, pixelShader);
            checkGlError("glAttachShader");
            //链接程序
            GLES20.glLinkProgram(program);
            //存放链接成功program数量的数组
            int[] linkStatus = new int[1];
            //获取program的链接情况
            GLES20.glGetProgramiv(program, GLES20.GL_LINK_STATUS, linkStatus, 0);
            //若链接失败则报错并删除程序
            if (linkStatus[0] != GLES20.GL_TRUE)
            {
                Log.e("ES20_ERROR", "Could not link program: ");
                Log.e("ES20_ERROR", GLES20.glGetProgramInfoLog(program));
                GLES20.glDeleteProgram(program);
                program = 0;
            }
        }
        return program;
    }


    /**
     * 加载制定shader的方法
     * @param shaderType shader的类型  GLES20.GL_VERTEX_SHADER   GLES20.GL_FRAGMENT_SHADER
     * @param sourceCode shader的脚本
     * @return shader索引
     */
    private static int loadShader(int shaderType,String sourceCode) {
        // 创建一个新shader
        int shader = GLES20.glCreateShader(shaderType);
        // 若创建成功则加载shader
        if (shader != 0) {
            // 加载shader的源代码
            GLES20.glShaderSource(shader, sourceCode);
            // 编译shader
            GLES20.glCompileShader(shader);
            // 存放编译成功shader数量的数组
            int[] compiled = new int[1];
            // 获取Shader的编译情况
            GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, compiled, 0);
            if (compiled[0] == 0) {//若编译失败则显示错误日志并删除此shader
                Log.e("ES20_ERROR", "Could not compile shader " + shaderType + ":");
                Log.e("ES20_ERROR", GLES20.glGetShaderInfoLog(shader));
                GLES20.glDeleteShader(shader);
                shader = 0;
            }
        }
        return shader;
    }

    //检查每一步操作是否有错误的方法
    public static void checkGlError(String op)
    {
        int error;
        while ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR)
        {
            Log.e("ES20_ERROR", op + ": glError " + error);
            throw new RuntimeException(op + ": glError " + error);
        }
    }
}
