package com.asuper.aidldemo.actitvity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.asuper.aidldemo.R;
import com.asuper.aidldemo.zxing.QRCodeUtil;

import java.io.File;

/**
 * Created by Super on 2016/10/21.
 */

public class ToolBarActivity extends AppCompatActivity {
    private static final String TAG = "ToolBarActivity";

    private Toolbar mToolbar;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_tool_bar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setNavigationIcon(R.mipmap.ic_launcher);//设置导航栏图标
        toolbar.setLogo(R.mipmap.kk_plugin_screen_red_dot);//设置app logo
        toolbar.setTitle("Title");//设置主标题
        toolbar.setSubtitle("Subtitle");//设置子标题

        toolbar.inflateMenu(R.menu.base_tool_menu);//设置右上角的填充菜单
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int menuItemId = item.getItemId();
                if (menuItemId == R.id.action_search) {
                    Toast.makeText(ToolBarActivity.this , "第一", Toast.LENGTH_SHORT).show();

                } else if (menuItemId == R.id.action_notification) {
                    Toast.makeText(ToolBarActivity.this , "第二" , Toast.LENGTH_SHORT).show();

                } else if (menuItemId == R.id.action_item1) {
                    Toast.makeText(ToolBarActivity.this , "第三", Toast.LENGTH_SHORT).show();

                } else if (menuItemId == R.id.action_item2) {
                    Toast.makeText(ToolBarActivity.this , "第四", Toast.LENGTH_SHORT).show();

                }
                return true;
            }
        });

        getWindow().setBackgroundDrawable(null);
        mImageView = (ImageView) findViewById(R.id.image);
        final String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator
                + "qr_" + System.currentTimeMillis() + ".jpg";

        //二维码图片较大时，生成图片、保存文件的时间可能较长，因此放在新线程中
        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean success = QRCodeUtil.createQRImage("www.baidu.com", 800, 800,
                         BitmapFactory.decodeResource(getResources(), R.mipmap.bang_start_tip),
                        filePath);

                if (success) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mImageView.setImageBitmap(BitmapFactory.decodeFile(filePath));
                        }
                    });
                }
            }
        }).start();

    }

    @Override
    public MenuInflater getMenuInflater() {
        return super.getMenuInflater();
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
