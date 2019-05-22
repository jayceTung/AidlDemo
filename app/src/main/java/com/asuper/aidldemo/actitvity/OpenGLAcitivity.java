package com.asuper.aidldemo.actitvity;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;

import com.asuper.aidldemo.R;
import com.asuper.aidldemo.opengl.SampleRender;

/**
 * @author super
 * @date 2019-04-28
 */
public class OpenGLAcitivity extends AppCompatActivity {
    private static final String TAG = "GLViewMediaActivity";
    private GLSurfaceView glView;
    public static final String videoPath = Environment.getExternalStorageDirectory().getPath() + "/Movies/早早早.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_opengl);
        glView = (GLSurfaceView) findViewById(R.id.surface_view);
        glView.setEGLContextClientVersion(2);
        SampleRender glVideoRenderer = new SampleRender(this, videoPath);
        glView.setRenderer(glVideoRenderer);
    }
}
