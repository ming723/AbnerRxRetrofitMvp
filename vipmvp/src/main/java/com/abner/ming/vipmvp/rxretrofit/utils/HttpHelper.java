package com.abner.ming.vipmvp.rxretrofit.utils;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;

import com.abner.ming.vipmvp.dialog.DialogAssigner;
import com.abner.ming.vipmvp.dialog.StyledDialog;
import com.abner.ming.vipmvp.rxretrofit.AbnerApplication;
import com.abner.ming.vipmvp.rxretrofit.listener.HttpJavaBeanCallBackListener;
import com.abner.ming.vipmvp.rxretrofit.listener.HttpStringCallBackListener;
import com.abner.ming.vipmvp.rxretrofit.model.IModel;
import com.abner.ming.vipmvp.rxretrofit.service.RetrofitService;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by AbnerMing on 2018/1/2.
 * 获取字符串或者JavaBean类
 */

public class HttpHelper {
    private RetrofitService mRetrofitService;
    private boolean isShow;
    private boolean cache;
    private Activity activity;

    public HttpHelper(Map<String, String> map, Activity activity) {
        mRetrofitService = HttpUtils.getmHttpUtils().getRetrofit(map).create(RetrofitService.class);
        this.activity = activity;
    }

    /**
     * isShow为true，展示加载框
     */
    public void setHttpShow(boolean isShow) {
        this.isShow = isShow;
    }
    /**
     * cache，无网状态下加载缓存
     */
    public void setCache(boolean cache) {
        this.cache = cache;
    }

    /**
     * 是否显示加载
     * */

    /**
     * get请求，返回字符串
     */
    public void getString(final String url, Map<String, String> map, final HttpStringCallBackListener listener) {
        String message = SharedPreUtils.getString(AbnerApplication.getInstance(), url.substring(url.length() - 3, url.length()));
        if (cache && !TextUtils.isEmpty(message) && !NetworkUtils.isConnected(AbnerApplication.getInstance())) {
            listener.success(message);
            return;
        }
        mRetrofitService.getRxRequest(url, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        if (isShow) {
                            show();
                        }
                        Log.i("HttpHelper", "onStart");
                    }

                    @Override
                    public void onCompleted() {
                        //关闭加载动画
                        dismiss();
                        Log.i("HttpHelper", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.failure(e.getMessage());
                        Log.i("HttpHelper", "onError");
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        Log.i("HttpHelper", "onNext");
                        try {
                            SharedPreUtils.put(AbnerApplication.getInstance(), url.substring(url.length() - 3, url.length()), responseBody.string());
                            listener.success(responseBody.string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    /**
     * post请求，返回字符串
     */
    public void postString(final String url, Map<String, String> map, final HttpStringCallBackListener listener) {
        String message = SharedPreUtils.getString(AbnerApplication.getInstance(), url.substring(url.length() - 3, url.length()));
        if (cache && !TextUtils.isEmpty(message) && !NetworkUtils.isConnected(AbnerApplication.getInstance())) {
            listener.success(message);
            return;
        }
        mRetrofitService.postRxRequest(url, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        //开启加载动画
                        if (isShow) {
                            show();
                        }
                    }

                    @Override
                    public void onCompleted() {
                        //关闭加载动画
                        dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.failure(e.getMessage());
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            SharedPreUtils.put(AbnerApplication.getInstance(), url.substring(url.length() - 3, url.length()), responseBody.string());
                            listener.success(responseBody.string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    /**
     * get请求，返回JavaBean
     */
    public <D extends IModel> void getJavaBean(final Class mClass, final String url, Map<String, String> map, final HttpJavaBeanCallBackListener listener) {
        String message = SharedPreUtils.getString(AbnerApplication.getInstance(), url.substring(url.length() - 3, url.length()));
        if (cache && !TextUtils.isEmpty(message) && !NetworkUtils.isConnected(AbnerApplication.getInstance())) {
            D clss = (D) new Gson().fromJson(message, mClass);
            listener.success(clss);
            return;
        }
        mRetrofitService.getRxRequest(url, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        //开启加载动画
                        if (isShow) {
                            show();
                        }
                    }

                    @Override
                    public void onCompleted() {
                        //关闭加载动画
                        dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.failure(e.getMessage());
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String json = responseBody.string();
                            SharedPreUtils.put(AbnerApplication.getInstance(), url.substring(url.length() - 3, url.length()), json);
                            D clss = (D) new Gson().fromJson(json, mClass);
                            listener.success(clss);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    /**
     * post请求，返回JavaBean
     */
    public <D extends IModel> void postJavaBean(final Class mClass, final String url, Map<String, String> map, final HttpJavaBeanCallBackListener listener) {
        String message = SharedPreUtils.getString(AbnerApplication.getInstance(), url.substring(url.length() - 3, url.length()));
        if (cache && !TextUtils.isEmpty(message) && !NetworkUtils.isConnected(AbnerApplication.getInstance())) {
            D clss = (D) new Gson().fromJson(message, mClass);
            listener.success(clss);
            return;
        }
        mRetrofitService.postRxRequest(url, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        //开启加载动画
                        if (isShow) {
                            show();
                        }
                    }

                    @Override
                    public void onCompleted() {
                        //关闭加载动画
                        dismiss();
                        Log.i("HttpHelper", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.failure(e.getMessage());
                        Log.i("HttpHelper", "onError");
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        Log.i("HttpHelper", "onNext");
                        try {
                            String json = responseBody.string();
                            SharedPreUtils.put(AbnerApplication.getInstance(), url.substring(url.length() - 3, url.length()), json);
                            D clss = (D) new Gson().fromJson(json, mClass);
                            listener.success(clss);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void show() {
        DialogAssigner.getInstance().assignLoading(activity, "正在加载……", true, false).show();

    }

    private void dismiss() {
        StyledDialog.dismissLoading();
    }

}
