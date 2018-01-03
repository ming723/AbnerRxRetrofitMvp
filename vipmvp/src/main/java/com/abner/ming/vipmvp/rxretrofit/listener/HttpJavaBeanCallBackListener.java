package com.abner.ming.vipmvp.rxretrofit.listener;


import com.abner.ming.vipmvp.rxretrofit.model.IModel;

/**
 * Created by AbnerMing on 2018/1/2.
 * 请求返回JavaBean回调
 */

public interface HttpJavaBeanCallBackListener<D extends IModel> {
    void success(D d);
    void failure(String error);
}
