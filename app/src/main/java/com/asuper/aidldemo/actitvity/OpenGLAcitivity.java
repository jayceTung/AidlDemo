package com.asuper.aidldemo.actitvity;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.Environment;
import androidx.appcompat.app.AppCompatActivity;

import com.asuper.aidldemo.R;
import com.asuper.aidldemo.opengl.TiAmoRender;

/**
 * @author super
 * @date 2019-04-28
 */
public class OpenGLAcitivity extends AppCompatActivity {
    private static final String TAG = "GLViewMediaActivity";
    private GLSurfaceView glView;
    public static final String videoPath = Environment.getExternalStorageDirectory().getPath() + "/output.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opengl);

        glView = (GLSurfaceView) findViewById(R.id.surface_view);


        glView.setEGLContextClientVersion(2);

        TiAmoRender mSampleRender = new TiAmoRender(this, videoPath);

        // Set the Renderer for drawing on the GLSurfaceView
        glView.setRenderer(mSampleRender);//设置renderer
    }
}
