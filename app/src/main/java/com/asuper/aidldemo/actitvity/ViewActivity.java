package com.asuper.aidldemo.actitvity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.asuper.aidldemo.R;
import com.asuper.aidldemo.view.CountDownView;
import com.asuper.aidldemo.view.MarqueeView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author super
 * @date 2017/12/14
 */
public class ViewActivity extends BaseActivity {

    private CountDownView mCdcvView;
    private Button mStartBtn;
    private MarqueeView marqueeView;
    private RelativeLayout mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        initView();
    }

    private void initView() {
        mView = (RelativeLayout) findViewById(R.id.rl_view);
        mCdcvView = (CountDownView) findViewById(R.id.cdv_view);
        mStartBtn = (Button) findViewById(R.id.btn_start);
        mStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCdcvView.start(10000);
            }
        });
        final List<String> list = new ArrayList<>();

        list.add("得分点");
        list.add("得分点");
        list.add("得分点");

        marqueeView = (MarqueeView) findViewById(R.id.mv_view);
        mView.getChildCount();

        mStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                marqueeView.stopScroll();
                marqueeView.addData(list);
                marqueeView.startScroll();
            }
        });

    }

    public boolean isOdd(int a){
        if(a%2 != 0){   //是奇数
            return true;
        }
        return false;
    }
}
