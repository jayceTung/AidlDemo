package com.asuper.aidldemo.actitvity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.asuper.aidldemo.R;
import com.asuper.aidldemo.eventbus.MessageEvent;
import com.asuper.aidldemo.okhttp.HeaderInterceptor;
import com.asuper.aidldemo.okhttp.LoggerInterceptor;
import com.asuper.aidldemo.parse.Util;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;

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

//    private TextView mTvText;
//    private Button mBt;
    private Button mBtTrue;
    private Button mBtFalse;

    @BindView(R.id.tv_text)
    TextView mTvText;
    @BindView(R.id.bt_click)
    Button mBt;

    private Unbinder mUnbinder;

    long[] mHits = new long[10];

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

        initView();
    }

    private void initView() {
        mTvText.setText("");
        mTvText.setVisibility(View.VISIBLE);
        mBt.setText("点我一下试试");
        mBt.setVisibility(View.VISIBLE);
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
