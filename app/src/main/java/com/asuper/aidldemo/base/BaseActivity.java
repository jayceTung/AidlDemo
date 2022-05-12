package com.asuper.aidldemo.base;

import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author super
 * @date 2018-12-07
 */
public abstract class BaseActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();

    private HashMap<Integer, RequestPermissionCallback> permissionCallbackHashMap = new HashMap<>();
    private List<Integer> requestCodes = new ArrayList<>();

    /**
     * 申请成功的回调接口
     */
    public interface RequestPermissionCallback {
        void onRequestCallback();
    }

    private String[] findDeniedPermissions(String... permissions) {
        List<String> permissionList = new ArrayList<>();
        for (String perm : permissions) {
            if (ContextCompat.checkSelfPermission(this, perm) !=
                    PackageManager.PERMISSION_GRANTED) {
                permissionList.add(perm);
            }
        }
        return permissionList.toArray(new String[permissionList.size()]);
    }

    protected void hasPermissions(int requestCode, RequestPermissionCallback callback, String... permissions) {
        permissionCallbackHashMap.put(requestCode, callback);
        requestCodes.add(requestCode);
        String[] deniedPermissions = findDeniedPermissions(permissions);
        if (deniedPermissions.length > 0) {
            ActivityCompat.requestPermissions(this, permissions, requestCode);
        } else {
            callback.onRequestCallback();
        }
    }

    private boolean verifyPermissions(int[] grantResults) {
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean grandResult = verifyPermissions(grantResults);
        for (int code : requestCodes) {
            if (code == requestCode) {
                if (grandResult) {
                    permissionCallbackHashMap.get(code).onRequestCallback();
                } else {
                    ActivityCompat.requestPermissions(this, permissions, requestCode);
                }
            }
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        initView();
        initEvent();
    }

    protected abstract int getContentView();

    protected void initView() {
        Log.i(TAG, "initView()");
    }

    protected void initEvent() {
        Log.i(TAG, "initEvent");
    }
}
