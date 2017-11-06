package com.androidweblottery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidweblottery.utils.CacheUtils;
import com.androidweblottery.utils.ConstantUtils;
import com.androidweblottery.utils.WebViewUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lifan on 2017/11/5.
 */

public class HomeActivity extends BaseActivity {

    @BindView(R.id.back)
    RelativeLayout mBack;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.rl_title)
    RelativeLayout mRlTitle;
    @BindView(R.id.webview_wap)
    WebView mWebView;

    private static final String TAG = "HomeActivity";

    private final String URL_FUN = "http://api10.wfc0000.com";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        startProgress();
        initWebView();
    }

    /**
     * webView配置
     */
    private void initWebView() {

        WebViewUtil.getWebSetting(mWebView, this);
        mWebView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if(newProgress == 100){
                    endProgress();
                    mWebView.setVisibility(View.VISIBLE);
                }
            }
        });
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                endProgress();
                mWebView.setVisibility(View.VISIBLE);
            }

        });
        
        String url = CacheUtils.getString(this, ConstantUtils.URL,URL_FUN);

        Log.d(TAG, "initWebView: " + url);
        mWebView.loadUrl(url);
    }





}
