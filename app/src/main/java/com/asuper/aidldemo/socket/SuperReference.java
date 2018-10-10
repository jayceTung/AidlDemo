package com.asuper.aidldemo.socket;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @author super
 * @date 2018/10/9
 */
public class SuperReference<T> extends WeakReference<T> {

    public SuperReference(T referent, ReferenceQueue<? super T> q) {
        super(referent, q);
    }
}
