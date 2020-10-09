package com.asuper.aidldemo.actitvity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.asuper.aidldemo.R;
import com.asuper.aidldemo.databinding.ActivityMvvmBinding;
import com.asuper.aidldemo.mvvm.MVVMViewModel;

/**
 * @author super
 * @date 2020/10/9
 */
public class MVVMActivity extends BaseActivity {
    private ActivityMvvmBinding binding;
    private MVVMViewModel mvvmViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_mvvm);

        mvvmViewModel = new MVVMViewModel(getApplication(),binding);
        binding.setViewModel(mvvmViewModel);  //初始化viewModel
    }
}
