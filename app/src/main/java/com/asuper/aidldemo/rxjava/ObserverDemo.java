package com.asuper.aidldemo.rxjava;

import android.util.Log;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Super on 2016/10/20.
 */

public class ObserverDemo {
    private static final String TAG = "ObserverDemo";

    /**
     * @param observer
     *
     * onStart(): 这是 Subscriber 增加的方法。它会在 subscribe 刚开始，而事件还未发送之前被调用，可以用于做一些
     * 准备工作，例如数据的清零或重置。这是一个可选方法，默认情况下它的实现为空。需要注意的是，如果对准备工作的线程有
     * 要求（例如弹出一个显示进度的对话框，这必须在主线程执行）， onStart() 就不适用了，因为它总是在 subscribe
     * 所发生的线程被调用，而不能指定线程。要在指定的线程来做准备工作，可以使用 doOnSubscribe() 方法，具体可以\
     * 在后面的文中看到。unsubscribe(): 这是 Subscriber 所实现的另一个接口 Subscription 的方法，用于取消订阅
     * 。在这个方法被调用后，Subscriber 将不再接收事件。一般在这个方法调用前，可以使用 isUnsubscribed() 先判断
     * 一下状态。 unsubscribe() 这个方法很重要，因为在 subscribe() 之后， Observable 会持有 Subscriber 的
     * 引用，这个引用如果不能及时被释放，将有内存泄露的风险。所以最好保持一个原则：要在不再使用的时候尽快在合适的地方
     * （例如 onPause() onStop() 等方法中）调用 unsubscribe() 来解除引用关系，以避免内存泄露的发生。
     */
    Observer<String> observer = new Observer<String>() {
        @Override
        public void onCompleted() {
            Log.i(TAG, "onCompleted");
        }

        @Override
        public void onError(Throwable e) {
            Log.w(TAG, "onError");
        }

        @Override
        public void onNext(String s) {
            Log.i(TAG, "OnNext");
        }
    };

    /**
     * @param subscriber
     */
    Subscriber<String> subscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {
            Log.i(TAG, "omCompleted");
        }

        @Override
        public void onError(Throwable e) {
            Log.w(TAG, "onError");
        }

        @Override
        public void onNext(String s) {
            Log.i(TAG, "onNext");
        }
    };


    Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
        @Override
        public void call(Subscriber<? super String> subscriber) {
            subscriber.onNext("first");
            subscriber.onNext("second");
            subscriber.onNext("third");
            subscriber.onCompleted();
        }
    });

    /**
     * subscribe
     */
    public void send() {
        observable.subscribe(observer);
        observable.subscribe(subscriber);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<String, Integer>() {
                    @Override
                    public Integer call(String s) {
                        return Integer.valueOf(s);
                    }
                })
                .filter(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return integer > 10;
                    }
                });
        Observable.just("1").map(new Func1<String, Object>() {
            @Override
            public Object call(String s) {
                return null;
            }
        });
    }
}
