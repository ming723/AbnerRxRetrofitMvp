package com.abner.ming.vipmvp.rxretrofit.utils;


import com.abner.ming.vipmvp.rxretrofit.AbnerApplication;
import com.abner.ming.vipmvp.rxretrofit.api.HttpApi;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by AbnerMing on 2018/1/2.
 * 联网工具类
 * getRetrofit()方法参数map，是否携带头部，不为null则是携带，为null不携带
 */

public class HttpUtils {
    private static final int DEFAULT_TIME_OUT = 5;//超时时间 5s

    private Retrofit retrofit;

    private Map<String, String> map=new HashMap<>();

    public Retrofit getRetrofit(Map<String, String> map) {
        this.map=map;
        return retrofit;
    }

    public void setRetrofit(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    private HttpUtils(){}
    public static HttpUtils mHttpUtils=null;
    public static HttpUtils getmHttpUtils(){
        if(mHttpUtils==null){
            mHttpUtils=new HttpUtils();
        }
        return mHttpUtils;
    }

    public void create(){
        if(retrofit==null){
            // 创建 OKHttpClient
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.cache(new Cache(new File(AbnerApplication.getInstance().getApplicationContext().getCacheDir(), "caches"), 1024 * 1024 * 100));
            builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);//连接超时时间
            builder.writeTimeout(DEFAULT_TIME_OUT,TimeUnit.SECONDS);//写操作 超时时间
            builder.readTimeout(DEFAULT_TIME_OUT,TimeUnit.SECONDS);//读操作超时时间
            // 添加公共参数拦截器
            if(map!=null){
                BasicParamsInterceptor basicParamsInterceptor = new BasicParamsInterceptor.Builder()
                        .addHeaderParamsMap(map)
                        .build();
                builder.addInterceptor(basicParamsInterceptor);
            }
            retrofit=new Retrofit.Builder().baseUrl(HttpApi.HTTP_URL)
                    .client(builder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }

    }
}
