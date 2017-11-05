package com.androidweblottery.httputil.exceptions;

/**
 * Created by sunshow on 2/16/16.
 */
public class ApiResponseException extends RuntimeException {

    /**
     * 错误码
     * 401000002、401000003、403000001、403000002、403000003又框架自行处理
     * 401000001需要UI层处理重新登录（全局）
     * 其他错误码客户端可直接弹出错误提示
     * 具体错误描述参看 - http://wiki.8win.com/pages/viewpage.action?pageId=2064507
     * */
    private String errorCode = "200";

    /**
     * 错误信息
     * 用于客户端提示显示
     * */
    private String errorMessage = "";

    /**
     * 错误详细描述
     * 可用于客户端提示显示，或直接保存记录日志
     * */
    private String errorDetail = "";

    public ApiResponseException() {

    }

    public ApiResponseException(String errorCode, String errorMessage, String errorDetail) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorDetail = errorDetail;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorDetail() {
        return errorDetail;
    }

    public void setErrorDetail(String errorDetail) {
        this.errorDetail = errorDetail;
    }
}
