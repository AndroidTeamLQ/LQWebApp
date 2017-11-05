package com.androidweblottery.utils;

import android.content.Context;
import android.os.Build;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by lifan on 2017/11/5.
 */

public class WebViewUtil {

    /**
     * 根据
     * @param webView
     * @return
     */
    public static WebSettings getWebSetting(WebView webView, Context context){
        if(webView !=null) {
            WebSettings websetting = webView.getSettings();
            websetting.setBuiltInZoomControls(true);
            websetting.setBlockNetworkImage(false);
            websetting.setJavaScriptEnabled(true);
            websetting.setSupportZoom(false);
            websetting.setCacheMode(WebSettings.LOAD_NO_CACHE);
            websetting.setDomStorageEnabled(true);
            // Set cache size to 8 mb by default. should be more than enough
            websetting.setAppCacheMaxSize(1024*1024*8);
            // This next one is crazy. It's the DEFAULT location for your app's cache
            // But it didn't work for me without this line.
            // UPDATE: no hardcoded path. Thanks to Kevin Hawkins
            String appCachePath = context.getApplicationContext().getCacheDir().getAbsolutePath();
            websetting.setAppCachePath(appCachePath);
            websetting.setAllowFileAccess(true);
            websetting.setAppCacheEnabled(true);
            if (Build.VERSION.SDK_INT >= 21) {
                websetting.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
            }

            //设置UA
//            String ua = websetting.getUserAgentString();
//            String uaAdded = "; ZYCP_ANDROID_V"+ BuildConfig.VERSION_CODE+"_P"+BuildConfig.product;
//            websetting.setUserAgentString(ua +uaAdded );

            return websetting;
        }
        return null;
    }
}
