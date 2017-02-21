package com.asuper.aidldemo.actitvity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import com.asuper.aidldemo.R;
import com.asuper.aidldemo.View.CountDownCircleView;
import com.asuper.aidldemo.View.GiftView;

/**
 * Created by Super on 2017/2/21.
 */

public class RecActivity extends AppCompatActivity implements RoomGesturer.GestureListener {
    private GiftView view;
    private RoomGesturer mGesturer;
    private CountDownCircleView mCountView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        view = (GiftView) findViewById(R.id.id_recycer_view_gift);
        mCountView = (CountDownCircleView) findViewById(R.id.id_count);
        mCountView.start(50);
        mGesturer = new RoomGesturer(this, this);
        view.setListener(new GiftView.RecyclerTouchListener() {
            @Override
            public void recyclerOnTouch(MotionEvent event) {
                mGesturer.getGestureDetector().onTouchEvent(event);
            }
        });

    }

    @Override
    public boolean onSingleTapUp() {
        return false;
    }

    @Override
    public void onLeftFling() {
        view.setFling(true);
    }

    @Override
    public void onRightFling() {
        view.setFling(false);
    }

    @Override
    public void onTopFling() {

    }

    @Override
    public void onDownFling() {

    }

    public void onBtn(View v) {
        view.initData();
    }

    public void onHide(View v) {

    }
}
