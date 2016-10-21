package com.asuper.aidldemo.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Created by Super on 2016/10/20.
 */

public class PointAdapter extends TypeAdapter<String> {
    private static final String TAG = "PointAdapter";

    private String str;

    @Override
    public void write(JsonWriter out, String value) throws IOException {
        if (value == null) {
            out.nullValue();
            return;
        }
        String str = value + "," + value;
    }

    @Override
    public String read(JsonReader in) throws IOException {
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return null;
        }
        String xy = in.nextString();
        String[] str = xy.split(",");
        return str[0];
    }
}
