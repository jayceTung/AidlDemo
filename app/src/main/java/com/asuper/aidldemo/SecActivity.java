package com.asuper.aidldemo;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.asuper.aidldemo.view.TriActivity;
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
    // 声明Notification
    private Notification notification ;
    // 声明NotificationManager
    private NotificationManager mNotification;

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
                intent.setClass(getApplicationContext(), TriActivity.class);
                startActivity(intent);
            }
        });

    }

    private void showNotification(){
        // 创建一个NotificationManager的引用
        NotificationManager notificationManager = (NotificationManager)
                this.getSystemService(android.content.Context.NOTIFICATION_SERVICE);

        // 定义Notification的各种属性
        Notification notification =new Notification(R.mipmap.ic_launcher,
                "督导系统", System.currentTimeMillis());
        //FLAG_AUTO_CANCEL   该通知能被状态栏的清除按钮给清除掉
        //FLAG_NO_CLEAR      该通知不能被状态栏的清除按钮给清除掉
        //FLAG_ONGOING_EVENT 通知放置在正在运行
        //FLAG_INSISTENT     是否一直进行，比如音乐一直播放，知道用户响应
        notification.flags |= Notification.FLAG_ONGOING_EVENT; // 将此通知放到通知栏的"Ongoing"即"正在运行"组中
        notification.flags |= Notification.FLAG_NO_CLEAR; // 表明在点击了通知栏中的"清除通知"后，此通知不清除，经常与FLAG_ONGOING_EVENT一起使用
        notification.flags |= Notification.FLAG_SHOW_LIGHTS;
        //DEFAULT_ALL     使用所有默认值，比如声音，震动，闪屏等等
        //DEFAULT_LIGHTS  使用默认闪光提示
        //DEFAULT_SOUNDS  使用默认提示声音
        //DEFAULT_VIBRATE 使用默认手机震动，需加上<uses-permission android:name="android.permission.VIBRATE" />权限
        notification.defaults = Notification.DEFAULT_LIGHTS;
        //叠加效果常量
        //notification.defaults=Notification.DEFAULT_LIGHTS|Notification.DEFAULT_SOUND;
        notification.ledARGB = Color.BLUE;
        notification.ledOnMS =5000; //闪光时间，毫秒

        // 设置通知的事件消息
        CharSequence contentTitle ="督导系统标题"; // 通知栏标题
        CharSequence contentText ="督导系统内容"; // 通知栏内容
        Intent notificationIntent =new Intent(getApplicationContext(), MainActivity.class); // 点击该通知后要跳转的Activity
        PendingIntent contentItent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
//        notification.setLatestEventInfo(this, contentTitle, contentText, contentItent);

        // 把Notification传递给NotificationManager
        notificationManager.notify(0, notification);
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
