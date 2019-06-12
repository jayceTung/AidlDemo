package com.asuper.aidldemo.opengl;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Environment;

/**
 * @author super
 * @date 2019-06-12
 */
public class VideoGLSurfaceView extends GLSurfaceView {
    private SampleRender mSampleRender;
    public static final String videoPath = Environment.getExternalStorageDirectory().getPath() + "/output.mp4";

    public VideoGLSurfaceView(Context context) {
        super(context);
        // Create an OpenGL ES 2.0 context
        setEGLContextClientVersion(2);

        mSampleRender = new SampleRender(context, videoPath);

        // Set the Renderer for drawing on the GLSurfaceView
        setRenderer(mSampleRender);
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }
}
