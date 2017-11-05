package com.androidweblottery;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.WindowManager;
import android.widget.ImageView;

import com.androidweblottery.utils.CacheUtils;
import com.androidweblottery.utils.ConstantUtils;
import com.androidweblottery.welcome.WelcomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends BaseActivity {

    private int mVersionCode = 5;

    private boolean mShouldShowGuidePage = false;

    @BindView(R.id.bg)
    ImageView mBg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //当系统版本为4.4或者4.4以上时可以使用沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);



        mBg.setBackgroundResource(R.mipmap.splash_bg);
        delayToNext();

    }

    /**
     * 进入主界面
     */
    protected void loadMainView() {

        int code = CacheUtils.getInt(this, ConstantUtils.VERSION_CODE, 0);
        boolean product = true;

        mShouldShowGuidePage = mVersionCode > code && product;


        if (mShouldShowGuidePage) { //进入引导页
            CacheUtils.putInt(this, ConstantUtils.VERSION_CODE, mVersionCode);
            Intent intent = new Intent(this, WelcomeActivity.class);
            startActivity(intent);
        } else {   //进入主页
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
        finish();
    }


    /**
     * 延迟操作
     */
    private void delayToNext() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                    loadMainView();
            }
        }, 1500);
    }
}
