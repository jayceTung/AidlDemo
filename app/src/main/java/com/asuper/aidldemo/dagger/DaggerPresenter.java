package com.asuper.aidldemo.dagger;

import javax.inject.Inject;

/**
 * Created by Super on 2017/4/17.
 */

public class DaggerPresenter {
    private DaggerView mDgView;

    @Inject
    public DaggerPresenter(DaggerView mDgView) {
        this.mDgView = mDgView;
    }

    public void showToast() {
        mDgView.showToast();
    }


}
