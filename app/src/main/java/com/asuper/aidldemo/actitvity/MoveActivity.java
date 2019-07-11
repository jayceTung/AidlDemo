package com.asuper.aidldemo.actitvity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.asuper.aidldemo.R;

/**
 * @author super
 * @date 2019-07-11
 */
public class MoveActivity extends AppCompatActivity {
    private View view1, view2, view3, view4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move);
        view1 = findViewById(R.id.view1);
        view2 = findViewById(R.id.view2);
        view3 = findViewById(R.id.view3);
        view4 = findViewById(R.id.view4);

        view1.setOnClickListener(v -> moveToTarget(view1, view4));
        view2.setOnClickListener(v -> moveToTarget(view2, view4));
        view3.setOnClickListener(v -> moveToTarget(view3, view4));

    }

    private void moveToTarget(View startView, View endView) {
        int[] start = new int[2];
        int[] end = new int[2];
        startView.getLocationOnScreen(start);
        endView.getLocationOnScreen(end);
        Log.i("dmc", "start x= " + start[0] + "endx =" + end[0]);
        Log.i("dmc", "start y= " + start[1] + "endx =" + end[1]);
        int directX = end[0] - start[0] + endView.getWidth() / 2;
        int directY = end[1] - start[1] + endView.getHeight() / 3;
        Log.i("dmc", "directx = " + directX + "directy = " + directY);
        AnimatorSet set = new AnimatorSet();
        ObjectAnimator translationX = ObjectAnimator.ofFloat(startView, "translationX", 0, directX);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(startView, "translationY", 0, directY);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(startView, "scaleX", 1.0f, 0.1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(startView, "scaleY", 1.0f, 0.1f);
        set.play(translationX).with(translationY).with(scaleX).with(scaleY);
        set.start();
    }
}
