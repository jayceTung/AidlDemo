package com.asuper.aidldemo.rxjava;

import android.util.Log;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by super on 2017/8/10.
 */

public class UseObserver {
    private static final String TAG = "UserObserver";

    Observer<String> observer = new Observer<String>() {
        @Override
        public void onCompleted() {
            Log.d(TAG, "onCompleted");
        }

        @Override
        public void onError(Throwable e) {
            Log.d(TAG, "onError");
        }

        @Override
        public void onNext(String s) {
            Log.d(TAG, "onNext");
        }
    };

    Subscriber<String> subscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {
            Log.d(TAG, "onCompleted");
        }

        @Override
        public void onError(Throwable e) {
            Log.d(TAG, "onError");
        }

        @Override
        public void onNext(String s) {
            Log.d(TAG, "onNext");
        }
    };

    Observable observable = Observable.create(new Observable.OnSubscribe() {
        @Override
        public void call(Object o) {

        }
    });

    public static void main(String[] args) {
        String[] name = {"1", "2", "3"};
        Observable.from(name).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });

        Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {
                String str = "1";
                System.out.println("thread name = " + Thread.currentThread().getName());
                System.out.println("onNext");
                subscriber.onNext(str);
                System.out.println("onCompleted");
                subscriber.onCompleted();
            }})
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        System.out.println("thread name = " + Thread.currentThread().getName());
                        return s + "update";
                    }
                })
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {

                    @Override
                    public void onCompleted() {
                        System.out.println("thread name = " + Thread.currentThread().getName());
                        System.out.println("onComp");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("thread name = " + Thread.currentThread().getName());
                        System.out.println("onNex");
                    }
                });
    }
}
