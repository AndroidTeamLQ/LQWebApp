package com.androidweblottery.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by secoo on 2017/11/5.
 */

public class Config extends BmobObject {
    private String appName;
    private String version;
    private String channel;
    private boolean goNative;
    private String url;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public boolean isGoNative() {
        return goNative;
    }

    public void setGoNative(boolean goNative) {
        this.goNative = goNative;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
