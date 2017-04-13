package com.asuper.aidldemo.actitvity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.asuper.aidldemo.R;
import com.asuper.aidldemo.dagger.DaggerMianConponet;
import com.asuper.aidldemo.dagger.MainModule;
import com.asuper.aidldemo.dagger.MianConponet;
import com.asuper.aidldemo.dagger.User;

import javax.inject.Inject;

/**
 * Created by Super on 17/4/13.
 */

public class DaggerActivity extends BaseActivity {

    private TextView mTvName;
    private Button mBtn;

    @Inject
    User mUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dagger);
        mTvName = (TextView) this.findViewById(R.id.tv_name);
        mBtn = (Button) this.findViewById(R.id.btn_name);
        MianConponet conponet = DaggerMianConponet.builder()
                .mainModule(new MainModule())
                .build();
        conponet.inject(this);

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvName.setText(mUser.getName());
            }
        });
    }
}
