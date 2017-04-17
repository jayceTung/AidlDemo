package com.asuper.aidldemo.actitvity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.asuper.aidldemo.R;
import com.asuper.aidldemo.dagger.DaggerGetComponent;
import com.asuper.aidldemo.dagger.DaggerModule;
import com.asuper.aidldemo.dagger.DaggerPresenter;
import com.asuper.aidldemo.dagger.DaggerView;
import com.asuper.aidldemo.dagger.GetComponent;

import javax.inject.Inject;

/**
 * Created by Super on 17/4/13.
 */

public class DaggerActivity extends BaseActivity implements DaggerView{

    private TextView mTvName;
    private Button mBtn;

    @Inject
    DaggerPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dagger);
        mTvName = (TextView) this.findViewById(R.id.tv_name);
        mBtn = (Button) this.findViewById(R.id.btn_name);

        GetComponent get = DaggerGetComponent.builder().daggerModule(new DaggerModule(this)).build();
        get.inject(this);

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast();
            }
        });

        mBtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                hideToast();
                return true;
            }
        });
    }

    @Override
    public void showToast() {
        mTvName.setText("show");
    }

    @Override
    public void hideToast() {
        mTvName.setText("hide");
    }
}
