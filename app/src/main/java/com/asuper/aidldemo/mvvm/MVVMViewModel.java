package com.asuper.aidldemo.mvvm;

import android.app.Application;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.asuper.aidldemo.BR;
import com.asuper.aidldemo.databinding.ActivityMvvmBinding;

/**
 * @author super
 * @date 2020/10/9
 */
public class MVVMViewModel extends BaseObservable {

    private ActivityMvvmBinding binding;
    private MVVMModel mvvmModel;
    private String Input;
    private String result;

    @Bindable
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
        notifyPropertyChanged(BR.result);
    }

    //    一般需要传入Application对象，方便在ViewModel中使用application
    //    比如sharedpreferences需要使用
    public MVVMViewModel(Application application, ActivityMvvmBinding binding) {
        this.binding=binding;
        mvvmModel = new MVVMModel();

    }

    public void getData(View view){
        Input = binding.etAccount.getText().toString();
        mvvmModel.getAccountData(Input, new MCallback() {
            @Override
            public void onSuccess(Account account) {
                String info = account.getName() + "|" + account.getLevel();
                setResult(info);
            }

            @Override
            public void onFailed() {
                setResult("消息获取失败");
            }
        });
    }
}
