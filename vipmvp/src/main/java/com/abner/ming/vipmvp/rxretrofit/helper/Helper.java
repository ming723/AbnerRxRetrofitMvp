package com.abner.ming.vipmvp.rxretrofit.helper;

import com.abner.ming.vipmvp.rxretrofit.listener.HelperListener;
import com.abner.ming.vipmvp.rxretrofit.listener.HttpJavaBeanCallBackListener;
import com.abner.ming.vipmvp.rxretrofit.listener.HttpStringCallBackListener;
import com.abner.ming.vipmvp.rxretrofit.model.IModel;
import com.abner.ming.vipmvp.rxretrofit.utils.HttpHelper;

import java.util.Map;

/**
 * Created by AbnerMing on 2018/1/3.
 */

public class Helper {
    private HttpHelper mHttpHelper;
    private HelperListener listener;
    public Helper(HttpHelper mHttpHelper,HelperListener listener){
        this.mHttpHelper=mHttpHelper;
        this.listener=listener;
    }
    /**
     * GET获取String请求
     */
    public void httpGetString(final int type, Map<String, String> map, String url) {
        mHttpHelper.getString(url, map, new HttpStringCallBackListener() {
            @Override
            public void success(String message) {
                listener.httpSuccess(type, message);
            }

            @Override
            public void failure(String error) {
                listener.httpFailure(type, error);
            }
        });
    }
    /**
     * POST获取String请求
     */
    public void httpPostString(final int type, Map<String, String> map, String url) {
        mHttpHelper.postString(url, map, new HttpStringCallBackListener() {
            @Override
            public void success(String message) {
                listener. httpSuccess(type, message);
            }

            @Override
            public void failure(String error) {
                listener.httpFailure(type, error);
            }
        });
    }

    /**
     * GET获取JavaBean请求
     */
    public <D extends IModel> void httpGetJavaBean(final int type, D d, Map<String, String> map, String url) {
        mHttpHelper.getJavaBean(d.getClass(), url, map, new HttpJavaBeanCallBackListener<D>() {
            @Override
            public void success(D d) {
                listener.httpJavaBeanSuccess(type, d);
            }

            @Override
            public void failure(String error) {
                listener. httpFailure(type, error);
            }
        });
    }

    /**
     * GET获取JavaBean请求
     */
    public <D extends IModel> void httpPostJavaBean(final int type, D d, Map<String, String> map, String url) {
        mHttpHelper.postJavaBean(d.getClass(), url, map, new HttpJavaBeanCallBackListener<D>() {
            @Override
            public void success(D d) {
                listener. httpJavaBeanSuccess(type, d);
            }

            @Override
            public void failure(String error) {
                listener. httpFailure(type, error);
            }
        });
    }
}
