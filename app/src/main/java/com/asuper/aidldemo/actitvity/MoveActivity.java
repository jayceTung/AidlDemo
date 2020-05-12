package com.asuper.aidldemo.actitvity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.asuper.aidldemo.R;
import com.asuper.aidldemo.fragment.CommentFragment;
import com.asuper.aidldemo.view.SearchHistView;

/**
 * @author super
 * @date 2019-07-11
 */
public class MoveActivity extends AppCompatActivity {
    private View view1, view2, view3, view4;
    private SearchHistView mShvView;

    public static String[] data = {"净水器", "手机", "电动车", "洗衣机", "沙发", "冰箱", "瓷砖", "空调", "床垫", "卫浴", "热水器", "床", "家具", "手表", "电视", "集成灶", "领带", "保温杯", "童装", "自行车", "空气净化器", "地板", "硅藻泥", "油烟机", "智能家居"};

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

        mShvView = (SearchHistView) findViewById(R.id.shv_view);
        setFlow();
    }

    public void setFlow(){
//        noSearch.setVisibility(View.GONE);
        for (int i = 0; i < data.length; i++) {
            final TextView textView = (TextView) LayoutInflater.from(this).inflate(R.layout.item_tv, mShvView, false);
            textView.setText(data[i]);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //自定义点击事件，这里我写出点击item执行删除操作
//                    for (int j = 0; j < data.length; j++) {
//                        if (data[i].equals(textView.getText().toString())) {
//                            data.remove(j);
//                            if (data.size() == 0){
//                                noSearch.setVisibility(View.VISIBLE);
//                            }
//                            flowView.removeView(v);
//                            StorageUtil.remove(SearchActivity.this,String.valueOf(j),false);
//                            break;
//                        }
//                    }
                }
            });
            mShvView.addView(textView);
        }
    }

    private void moveToTarget(View startView, View endView) {
//        int[] start = new int[2];
//        int[] end = new int[2];
//        startView.getLocationOnScreen(start);
//        endView.getLocationOnScreen(end);
//        Log.i("dmc", "start x= " + start[0] + "endx =" + end[0]);
//        Log.i("dmc", "start y= " + start[1] + "endx =" + end[1]);
//        int directX = end[0] - start[0] + endView.getWidth() / 2;
//        int directY = end[1] - start[1] + endView.getHeight() / 3;
//        Log.i("dmc", "directx = " + directX + "directy = " + directY);
//        AnimatorSet set = new AnimatorSet();
//        ObjectAnimator translationX = ObjectAnimator.ofFloat(startView, "translationX", 0, directX);
//        ObjectAnimator translationY = ObjectAnimator.ofFloat(startView, "translationY", 0, directY);
//        ObjectAnimator scaleX = ObjectAnimator.ofFloat(startView, "scaleX", 1.0f, 0.1f);
//        ObjectAnimator scaleY = ObjectAnimator.ofFloat(startView, "scaleY", 1.0f, 0.1f);
//        set.play(translationX).with(translationY).with(scaleX).with(scaleY);
//        set.start();
        CommentFragment fullSheetDialogFragment = new CommentFragment();
        fullSheetDialogFragment.show(getSupportFragmentManager(),"CommentFragment");
    }
}
