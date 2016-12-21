package com.asuper.aidldemo;

import android.animation.ObjectAnimator;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.CycleInterpolator;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.asuper.aidldemo.View.TipView;
import com.asuper.aidldemo.actitvity.ToolBarActivity;
import com.asuper.library.BarrageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ws.WebSocket;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private IMyAidlInterface myAidlInterface;
    private TextView mTextView;
    private HttpConnectionUrlFactory factory;
    private BarrageView view;
    private TipView tipView;
    private WebSocket mWebSocket;

    private RequestQueue mReqQueue;

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

        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(3000, TimeUnit.SECONDS)
                .writeTimeout(3000, TimeUnit.SECONDS)
                .connectTimeout(3000, TimeUnit.SECONDS)
                .build();

//        Request request1 = new Request.Builder().url("ws://room117.kktv8.com:50003/").build();
//        WebSocketCall call = WebSocketCall.create(client, request1);
//        call.enqueue(new WebSocketListener() {
//
//            @Override
//            public void onOpen(WebSocket webSocket, Response response) {
//                Log.d(TAG, "onOpen Response = " + response.toString());
//                mWebSocket = webSocket;
//            }
//
//            @Override
//            public void onFailure(IOException e, Response response) {
//                Log.d(TAG, "onFailure Response = " + response.toString());
//            }
//
//            @Override
//            public void onMessage(ResponseBody message) throws IOException {
//                Log.d(TAG, "ResponseBody = " + message.toString());
//            }
//
//            @Override
//            public void onPong(Buffer payload) {
//
//            }
//
//            @Override
//            public void onClose(int code, String reason) {
//
//            }
//        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("http://ures.kktv8.com/kktv/activity/image/575/20130424151316_797.jpg!160");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    Log.i(TAG, "code = " + connection.getResponseCode());


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        ViewCompat.animate(mTextView)
                .setDuration(200)
                .scaleX(0.9f)
                .scaleY(0.9f)
                .setInterpolator(new CycleInterpolator(1))
                .setListener(new ViewPropertyAnimatorListener() {
                    @Override
                    public void onAnimationStart(final View view) {

                    }

                    @Override
                    public void onAnimationEnd(final View view) {
                        switch (view.getId()) {
//                            case R.id.btn_horizontal_ntb:
//                                startActivity(
//                                        new Intent(MainActivity.this, HorizontalNtbActivity.class)
//                                );
//                                break;
//                            case R.id.btn_horizontal_top_ntb:
//                                startActivity(
//                                        new Intent(MainActivity.this, TopHorizontalNtbActivity.class)
//                                );
//                                break;
//                            case R.id.btn_vertical_ntb:
//                                startActivity(
//                                        new Intent(MainActivity.this, VerticalNtbActivity.class)
//                                );
//                                break;
//                            case R.id.btn_samples_ntb:
//                                startActivity(
//                                        new Intent(MainActivity.this, SamplesNtbActivity.class)
//                                );
//                                break;
                        }
                    }

                    @Override
                    public void onAnimationCancel(final View view) {

                    }
                })
                .withLayer()
                .start();

        mReqQueue = Volley.newRequestQueue(this);
        mReqQueue.add(new StringRequest("http://www.baidu.com", new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "onResponse = " + response.toString());
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "onErrorResponse = " + error.toString());
            }
        }));

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
        intent.setClass(this, ToolBarActivity.class);
        startActivity(intent);
    }

    interface HttpConnectionUrlFactory {
        HttpURLConnection build(URL url) throws IOException;
    }


}
