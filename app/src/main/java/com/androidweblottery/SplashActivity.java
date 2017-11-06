package com.androidweblottery;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;

import com.androidweblottery.bean.Config;
import com.androidweblottery.utils.AppUtils;
import com.androidweblottery.utils.CacheUtils;
import com.androidweblottery.utils.ConstantUtils;
import com.androidweblottery.utils.ManifestUtils;
import com.androidweblottery.utils.VersionUtils;
import com.androidweblottery.welcome.WelcomeActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

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
        reqConfig();


    }

    private void reqConfig() {
        BmobQuery<Config> query = new BmobQuery<>();
        query.addWhereEqualTo("appName", AppUtils.getAppName(this));
        Log.e("bmob", AppUtils.getAppName(this));
        Log.e("bmob", VersionUtils.getVersionName(this));
        Log.e("bmob", ManifestUtils.getMetaDataValue(this, "CHANNEL"));
        query.addWhereEqualTo("version", VersionUtils.getVersionName(this));
        query.addWhereEqualTo("channel", ManifestUtils.getMetaDataValue(this, "CHANNEL"));
        query.findObjects(new FindListener<Config>() {
            @Override
            public void done(List<Config> list, BmobException e) {
                if (list != null && list.size() > 0) {
                    Config configBean = list.get(0);
                    if (configBean.isGoNative()) {
                        //TODO
                    } else {
                        delayToNext();
                    }
                }
            }
        });
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
