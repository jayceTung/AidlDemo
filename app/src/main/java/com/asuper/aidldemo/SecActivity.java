package com.asuper.aidldemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;

/**
 * Created by Super on 2016/9/6.
 */
public class SecActivity extends Activity {
    private static final String TAG = SecActivity.class.getSimpleName();

    ImageView view;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        setContentView(R.layout.activity_sec);
        textView = (TextView) findViewById(R.id.text_view);


        RequestListener<String,GlideDrawable> errorListener=new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {

                Log.e("onException",e.toString()+"  model:"+model+" isFirstResource: "+isFirstResource);
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                Log.e("onResourceReady","isFromMemoryCache:"+isFromMemoryCache+"  model:"+model+" isFirstResource: "+isFirstResource);
                return false;
            }
        } ;

        SimpleTarget target = new SimpleTarget() {
            @Override
            public void onResourceReady(Object resource, GlideAnimation glideAnimation) {
                textView.setText(resource.toString());
            }
        };

        Glide.with(this).load("http://ures.kktv8.com/kktv/activity/image/575/20130424151316_797.jpg!160")
                .asBitmap().toBytes().into(target);


        view = (ImageView) findViewById(R.id.circle_view);
        Glide.with(this).load("http://img1.3lian.com/2015/w4/17/d/64.gif")
            .listener(errorListener).crossFade().into(view);
        Glide.with(this).load("http://ures.kktv8.com/kktv/activity/image/575/20130424151316_797.jpg!160")
                .asBitmap().toBytes().into(new SimpleTarget<byte[]>(320,150) {
            @Override
            public void onResourceReady(byte[] bytes, GlideAnimation<? super byte[]> arg1) {
                // TODO Auto-generated method stub
                System.out.println(bytes.length+"");
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                intent.setClass(getApplicationContext(), SecActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.i(TAG, "onNewIntent");
        super.onNewIntent(intent);
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "onDestroy");
        super.onDestroy();
    }
}
