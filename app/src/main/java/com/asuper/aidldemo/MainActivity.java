package com.asuper.aidldemo;

import android.animation.ObjectAnimator;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.asuper.aidldemo.View.TipView;
import com.asuper.library.BarrageView;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private IMyAidlInterface myAidlInterface;
    private TextView mTextView;
    private HttpConnectionUrlFactory factory;
    private BarrageView view;
    private TipView tipView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate");
        bindService();
        mTextView = (TextView) findViewById(R.id.text);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String result = myAidlInterface.printAndroidAidl("MainActivity");
//                    Snackbar.make(mTextView, result, Snackbar.LENGTH_SHORT).show();
                    tipView.start();
                    mTextView.setX(100);
                    ObjectAnimator ani = ObjectAnimator.ofFloat(mTextView, "translationY", mTextView.getY(), mTextView.getY() + 100);
                    ani.setDuration(100);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        tipView = (TipView) findViewById(R.id.tipView);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("http://ures.kktv8.com/kktv/activity/image/575/20130424151316_797.jpg!160");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    Log.i(TAG, "code = " + connection.getResponseCode());

                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(url)
                            .build();
                    Response response = client.newCall(request).execute();
                    Log.i(TAG, "okhttp code =" + response.code());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void bindService() {
        Intent intent = new Intent(MainActivity.this, MyService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }


    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myAidlInterface = IMyAidlInterface.Stub.asInterface(service);

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            myAidlInterface = null;
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }

    public void onBtn(View v) {
        Intent intent = getIntent();
        intent.setClass(this, SecActivity.class);
        startActivity(intent);
    }

    interface HttpConnectionUrlFactory {
        HttpURLConnection build(URL url) throws IOException;
    }
}
