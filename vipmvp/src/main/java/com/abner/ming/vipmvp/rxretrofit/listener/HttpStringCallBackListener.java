package com.abner.ming.vipmvp.rxretrofit.listener;

/**
 * Created by AbnerMing on 2018/1/2.
 * 请求返回字符串回调
 */

public interface HttpStringCallBackListener {
    void success(String message);
    void failure(String error);
}
