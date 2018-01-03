package com.abner.ming.vipmvp.rxretrofit;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.abner.ming.vipmvp.dialog.StyledDialog;
import com.abner.ming.vipmvp.rxretrofit.utils.HttpUtils;

/**
 * Created by AbnerMing on 2018/1/3.
 */

public class AbnerApplication extends MultiDexApplication {
    private static AbnerApplication INSTANCE;
    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        StyledDialog.init(this);
        HttpUtils.getmHttpUtils().create();
    }
    public static AbnerApplication getInstance(){
        if (INSTANCE == null){
            synchronized (AbnerApplication.class){
                INSTANCE = new AbnerApplication();
            }
        }
        return INSTANCE;
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }
}
