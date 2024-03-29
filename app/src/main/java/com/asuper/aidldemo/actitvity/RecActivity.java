package com.asuper.aidldemo.actitvity;

import android.content.ComponentName;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.Nullable;
import android.util.Log;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.asuper.aidldemo.R;
import com.asuper.aidldemo.eventbus.MessageEvent;
import com.asuper.aidldemo.okhttp.HeaderInterceptor;
import com.asuper.aidldemo.okhttp.LoggerInterceptor;
import com.asuper.aidldemo.parse.Util;
import com.asuper.aidldemo.scheduler.PollService;
import com.asuper.aidldemo.view.DrawLayout;
import com.asuper.aidldemo.view.DrawView;
import com.asuper.aidldemo.view.LoadingAvatarView;
import com.asuper.aidldemo.view.LoopNewsView;
import com.asuper.aidldemo.view.WaveView;
import com.asuper.aidldemo.view.dispatchview.SuperView;
import com.asuper.aidldemo.view.dispatchview.SuperViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Super on 2017/2/21.
 */

public class RecActivity extends BaseActivity {
    private static final String TAG = "RecActivity";
    public static final int MSG_MES = 0x000001;

//    private TextView mTvText;
//    private Button mBt;
    private Button mBtTrue;
    private Button mBtFalse;

    @BindView(R.id.tv_text)
    TextView mTvText;
    @BindView(R.id.bt_click)
    Button mBt;
    @BindView(R.id.wv_circle)
    WaveView mCircle;
    RelativeLayout mRootView;
    SuperView svView;
    SuperViewGroup svgView;
    DrawView drawView;
    DrawView drawView2;
    DrawLayout dl;
    LoadingAvatarView mLAView;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    private Unbinder mUnbinder;
    private PollService mPollService;
    private ComponentName mComponentName;
    private int jobId;

    long[] mHits = new long[10];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        Util.sysncIsDebug(this);
        super.onCreate(savedInstanceState);

        mUnbinder = ButterKnife.bind(this);

        this.getWindow().setBackgroundDrawable(null);

        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_recycler);
        Log.i(TAG, Util.isDebug.booleanValue() + "");

        boolean matches = Patterns.WEB_URL.matcher("www.baidu.com").matches();

        OkHttpClient client = new OkHttpClient().newBuilder()
                .addInterceptor(new HeaderInterceptor())
                .addInterceptor(new LoggerInterceptor()).build();
        Request.Builder builder = new Request.Builder();
        Request request = builder.url("http://www.baidu.com").build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("Request", call.request().toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("Request", response.body().toString());
                response.body().close();
            }
        });

        mTvText = (TextView) this.findViewById(R.id.tv_text);
        mBt = (Button) this.findViewById(R.id.bt_click);
        mBtTrue = (Button) this.findViewById(R.id.bt_true);
        mBtFalse = (Button) this.findViewById(R.id.bt_false);
        mCircle = (WaveView) this.findViewById(R.id.wv_circle);
        svgView = (SuperViewGroup) this.findViewById(R.id.svg_group);
        svView = (SuperView) this.findViewById(R.id.sv_view);
        drawView = (DrawView) this.findViewById(R.id.draw);
        drawView2 = (DrawView) this.findViewById(R.id.draw2);
        dl = (DrawLayout) this.findViewById(R.id.dl_layout);
        mLAView = (LoadingAvatarView) this.findViewById(R.id.lav_view);
        mLAView.setImage();
        LoopNewsView view = (LoopNewsView) this.findViewById(R.id.lnv);
        List<String> list = new ArrayList<String>();
        list.add("11111111111");
        list.add("11111111112");
        list.add("11111111113");
        list.add("11111111114");
        list.add("11111111115");
        list.add("11111111116");
        list.add("11111111117");
        list.add("11111111118");
        list.add("11111111119");
        list.add("11111111121");
        list.add("11111111131");
        view.addData(list);

        initView();

    }

    private void initView() {
        mTvText.setText("");
        mTvText.setVisibility(View.VISIBLE);
        mBt.setText("点我一下试试");
        mBt.setVisibility(View.VISIBLE);
        mBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = getIntent();
//                intent.setClass(getApplicationContext(), InnerService.class);
//                startService(intent);
//
//                Rest.getInstance().create(NetService.class)
//                        .getCountt("yuantong", "11111111111")
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new Subscriber<KDBean>() {
//                            @Override
//                            public void onCompleted() {
//
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//                                Log.i(TAG, e.getMessage());
//                            }
//
//                            @Override
//                            public void onNext(KDBean kdBean) {
//                                mTvText.setText(kdBean.toString());
//                            }
//                        });

//                mCircle.start();
//                drawView.start();
//                drawView2.start();
                dl.shuffle();
            }
        });
        mBtTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dl.deal();
            }
        });
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Log.i("super", "thread name = " + Thread.currentThread().getName());
            }
        });
        mRootView = (RelativeLayout) findViewById(R.id.root_view);
//        WaitViewController.from(mRootView).renderChilds();
//        WaitViewController.from(mBt).render();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "OnResume");
    }

    @Override
    protected void onDestroy() {
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        super.onDestroy();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i("SuperActivity", "dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("SuperActivity", "onTouchEvent");
        return super.onTouchEvent(event);
    }

    /**
     * 无论从那个线程发布的事件都会在UI线程中执行
     * ThreadMode.MAIN
     * @param event
     * 对应低版本的onEventMainThread方法
     */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onEventMain(MessageEvent event) {
        if(event != null){
            Toast.makeText(this, event.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

}
