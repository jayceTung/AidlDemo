package com.asuper.aidldemo.actitvity;

import android.os.Bundle;

import com.asuper.aidldemo.R;
import com.asuper.aidldemo.bean.OrderLogisticsBean;
import com.asuper.aidldemo.databinding.ActivityMvvmBinding;
import com.asuper.aidldemo.gson.GsonUtil;
import com.asuper.aidldemo.mvvm.MVVMViewModel;
import com.asuper.aidldemo.view.LogisticsView;

/**
 * @author super
 * @date 2020/10/9
 */
public class MVVMActivity extends BaseActivity {
    private ActivityMvvmBinding binding;
    private MVVMViewModel mvvmViewModel;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        binding = DataBindingUtil.setContentView(this,R.layout.activity_mvvm);
//
//        mvvmViewModel = new MVVMViewModel(getApplication(),binding);
//        binding.setViewModel(mvvmViewModel);  //初始化viewModel
//    }

    private LogisticsView logisticsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logistics);
        logisticsView = findViewById(R.id.logisticsView);
        logisticsView.setLogisticsAdapter(intData());

    }

    private OrderLogisticsBean intData() {
        String data = "{\n" +
                "\t\"TagCode\": \"\",\n" +
                "\t\"TagMsg\": \"\",\n" +
                "\t\"expressCompany\": \"\",\n" +
                "\t\"location\": \"\",\n" +
                "\t\"logisticCode\": \"\",\n" +
                "\t\"orderCode\": \"\",\n" +
                "\t\"orderState\": 0,\n" +
                "\t\"reason\": \"\",\n" +
                "\t\"shipperCode\": \"\",\n" +
                "\t\"state\": 0,\n" +
                "\t\"stateEx\": 0,\n" +
                "\t\"traces\": [\n" +
                "\t\t{\n" +
                "\t\t\t\"acceptStation\": \"都是房价阿里发酵开了发15990010346\",\n" +
                "\t\t\t\"acceptTime\": \"1605085668000\",\n" +
                "\t\t\t\"action\": \"\",\n" +
                "\t\t\t\"location\": \"\",\n" +
                "\t\t\t\"remark\": \"\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"acceptStation\": \"都是房价阿里发酵开了发15990010346\",\n" +
                "\t\t\t\"acceptTime\": \"1605085668000\",\n" +
                "\t\t\t\"action\": \"\",\n" +
                "\t\t\t\"location\": \"\",\n" +
                "\t\t\t\"remark\": \"\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"acceptStation\": \"都是房价阿里发酵开了发15990010346\",\n" +
                "\t\t\t\"acceptTime\": \"1605085668000\",\n" +
                "\t\t\t\"action\": \"\",\n" +
                "\t\t\t\"location\": \"\",\n" +
                "\t\t\t\"remark\": \"\"\n" +
                "\t\t},{\n" +
                "\t\t\t\"acceptStation\": \"都是房价阿里发酵开了发15990010346\",\n" +
                "\t\t\t\"acceptTime\": \"1605085668000\",\n" +
                "\t\t\t\"action\": \"\",\n" +
                "\t\t\t\"location\": \"\",\n" +
                "\t\t\t\"remark\": \"\"\n" +
                "\t\t},{\n" +
                "\t\t\t\"acceptStation\": \"都是房价阿里发酵开了发\",\n" +
                "\t\t\t\"acceptTime\": \"1605085668000\",\n" +
                "\t\t\t\"action\": \"\",\n" +
                "\t\t\t\"location\": \"\",\n" +
                "\t\t\t\"remark\": \"\"\n" +
                "\t\t},{\n" +
                "\t\t\t\"acceptStation\": \"都是房价阿里发酵开了发\",\n" +
                "\t\t\t\"acceptTime\": \"1605085668000\",\n" +
                "\t\t\t\"action\": \"\",\n" +
                "\t\t\t\"location\": \"\",\n" +
                "\t\t\t\"remark\": \"\"\n" +
                "\t\t},{\n" +
                "\t\t\t\"acceptStation\": \"都是房价阿里发酵开了发\",\n" +
                "\t\t\t\"acceptTime\": \"1605085668000\",\n" +
                "\t\t\t\"action\": \"\",\n" +
                "\t\t\t\"location\": \"\",\n" +
                "\t\t\t\"remark\": \"\"\n" +
                "\t\t}\n" +
                "\t]\n" +
                "}";
        return GsonUtil.GsonToBean(data, OrderLogisticsBean.class);
    }
}
