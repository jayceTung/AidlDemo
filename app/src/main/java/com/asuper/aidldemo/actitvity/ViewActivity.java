package com.asuper.aidldemo.actitvity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.asuper.aidldemo.R;
import com.asuper.aidldemo.view.CountDownView;

/**
 * @author super
 * @date 2017/12/14
 */
public class ViewActivity extends BaseActivity {

    private CountDownView mCdcvView;
    private Button mStartBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        initView();
    }

    private void initView() {
        mCdcvView = (CountDownView) findViewById(R.id.cdv_view);
        mStartBtn = (Button) findViewById(R.id.btn_start);
        mStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCdcvView.start(10000);
            }
        });
    }
}
