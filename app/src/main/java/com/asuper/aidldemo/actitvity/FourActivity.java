package com.asuper.aidldemo.actitvity;

import android.app.ActivityManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.asuper.aidldemo.R;
import com.asuper.aidldemo.base.BaseActivity;
import com.asuper.aidldemo.view.LoopNewsView;
import com.asuper.aidldemo.view.LoopView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author super
 * @date 2018-12-07
 */
public class FourActivity extends BaseActivity {

    private LoopNewsView mLnvView;
    private TextView mTvView;
    private Button mBtn;

    @Override
    protected int getContentView() {
        return R.layout.activity_four_layout;
    }

    @Override
    protected void initView() {
        super.initView();
        mLnvView = (LoopNewsView) this.findViewById(R.id.lnv_view);
        List<String> list = new ArrayList<String>();
        list.add("111111111111111111");
        list.add("111111111121111111");
        list.add("111111111131111111");
        list.add("111111111141111111");
        list.add("111111111151111111");
        list.add("111111111161111111");
        list.add("111111111171111111");
        list.add("111111111181111111");
        list.add("111111111191111111");
        list.add("111111111211111111");
        list.add("111111111311111111");
        mLnvView.addData(list);

        mTvView = (TextView) this.findViewById(R.id.tv_view);
        mTvView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        mTvView.setSingleLine(true);
        mTvView.setFocusable(true);
        mTvView.setText("111111111111111111111111111111111112222211111111111111111111111111111111111");

        mBtn = (Button) findViewById(R.id.btn);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTvView.hasFocus()) {
                    mTvView.setFocusableInTouchMode(false);
                    mTvView.setFocusable(false);
                } else {
                    mTvView.setFocusable(true);
                    mTvView.setFocusableInTouchMode(true);
                }
            }
        });
        LoopView lv = (LoopView) this.findViewById(R.id.lv_layout);
        lv.addData(list);

        //进程优先级
        int process = ActivityManager.RunningAppProcessInfo.IMPORTANCE_BACKGROUND;
    }
}
