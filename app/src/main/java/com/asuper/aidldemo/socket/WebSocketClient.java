package com.asuper.aidldemo.socket;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.ws.WebSocket;
import okhttp3.ws.WebSocketCall;
import okhttp3.ws.WebSocketListener;
import okio.Buffer;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Super on 2016/12/22.
 */

public class WebSocketClient implements WebSocketListener {
    private static final String TAG = "WebSocketClient";

    private static final int SET_TIME = 3000;

    private WebSocket mWebSocket;
    private SocketCallback mCallback;

    public WebSocketClient() {
    }

    public void open(String url, SocketCallback callback) {
        initSocket(url);
        this.mCallback = callback;
    }

    private void initSocket(String url) {
        OkHttpClient client = new OkHttpClient.Builder()
                .writeTimeout(SET_TIME, TimeUnit.SECONDS)
                .readTimeout(SET_TIME, TimeUnit.SECONDS)
                .connectTimeout(SET_TIME, TimeUnit.SECONDS)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .build();

        WebSocketCall call = WebSocketCall.create(client, request);
        call.enqueue(this);
        client.dispatcher().executorService().shutdown();
    }

    public void sendMessage(String msg) {
        Observable.just(msg)
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        try {
                            Log.d(TAG, "sendMessage = " + s);
                            mWebSocket.sendMessage(RequestBody.create(WebSocket.TEXT, s));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return s;
                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        //nop
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, "send message" + s);
                    }
                });
    }

    public void close() {
        Observable.just(null)
                .map(new Func1<Object, Void>() {
                    @Override
                    public Void call(Object o) {
                        try {
                            mWebSocket.close(1000, "bye bye");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Void>() {
                    @Override
                    public void onCompleted() {
                        //nop
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Void aVoid) {
                        Log.d(TAG, "close websocket");
                    }
                });
    }

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        this.mWebSocket = webSocket;
        Log.d(TAG, "onOpen Response = " + response.toString());
    }

    @Override
    public void onFailure(IOException e, Response response) {
        Log.e(TAG, "onFailure = " + e.toString());
    }

    @Override
    public void onMessage(ResponseBody message) {
//        Observable.just(message)
//                .observeOn(Schedulers.io())
//                .subscribeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<ResponseBody>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(ResponseBody responseBody) {
//                        mCallback.onMessage(responseBody.toString());
//                    }
//                });
        mCallback.onMessage(message.toString());
    }

    @Override
    public void onPong(Buffer payload) {
        Log.d(TAG, "onPong = " + payload.readByteString().toString());
    }

    @Override
    public void onClose(int code, String reason) {
        Log.d(TAG, "onClose = " + code + "\nreason = " + reason);
    }

    public interface SocketCallback {
        // socket返回消息
        void onMessage(String message);
    }
}
