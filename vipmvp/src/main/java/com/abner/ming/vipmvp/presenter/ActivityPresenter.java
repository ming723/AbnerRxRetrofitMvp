package com.abner.ming.vipmvp.presenter;

import android.os.Bundle;
import android.view.View;

import com.abner.ming.vipmvp.base.BaseActivityHttp;
import com.abner.ming.vipmvp.view.IDelegate;

/**
 * Created by AbnerMing on 2018/1/3.
 */

public abstract class ActivityPresenter<T extends IDelegate> extends BaseActivityHttp {


    @Override
    protected View layoutView(Bundle savedInstanceState) {
        delegate.create(getLayoutInflater(), null, savedInstanceState);
        return delegate.getRootView();
    }

    @Override
    protected void initData() {
        delegate.initWeight();
        bindEnvsListener();
    }

    public T delegate;

    public abstract Class<T> getDelegateClass();

    public ActivityPresenter() {
        try {
            delegate = getDelegateClass().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void bindEnvsListener() {
    }
}
