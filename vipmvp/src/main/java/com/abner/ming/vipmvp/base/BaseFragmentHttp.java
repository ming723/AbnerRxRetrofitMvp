package com.abner.ming.vipmvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.abner.ming.vipmvp.rxretrofit.helper.Helper;
import com.abner.ming.vipmvp.rxretrofit.listener.HelperListener;
import com.abner.ming.vipmvp.rxretrofit.model.IModel;
import com.abner.ming.vipmvp.rxretrofit.utils.HttpHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by AbnerMing on 17/8/1.
 * Fragment有网络请求可集成此类
 */

public abstract class BaseFragmentHttp extends Fragment implements HelperListener {
    private HttpHelper mHttpHelper;
    private Helper mHelper;

    protected abstract View layoutView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);
    protected abstract void initView();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = layoutView(inflater, container, savedInstanceState);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mHttpHelper = new HttpHelper(headerMap, getActivity());
        mHelper=new Helper(mHttpHelper,this);
        initView();
    }

    protected void toast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
    }

    private Map<String, String> headerMap = new HashMap<>();

    /**
     * 添加头部信息
     */
    public void setHeaderMap(Map<String, String> headerMap) {
        this.headerMap = headerMap;
    }

    /**
     * 是否显示加载框
     */
    public void isHttpShow(boolean isShow) {
        mHttpHelper.setHttpShow(isShow);
    }

    /**
     * GET获取String请求
     */
    public void httpGetString( int type, Map<String, String> map, String url) {
        mHelper.httpGetString(type,map,url);
    }

    /**
     * POST获取String请求
     */
    public void httpPostString(final int type, Map<String, String> map, String url) {
        mHelper.httpPostString(type,map,url);
    }

    /**
     * GET获取JavaBean请求
     */
    public <D extends IModel> void httpGetJavaBean(final int type, D d, Map<String, String> map, String url) {
        mHelper.httpGetJavaBean(type,d,map,url);
    }

    /**
     * GET获取JavaBean请求
     */
    public <D extends IModel> void httpPostJavaBean(final int type, D d, Map<String, String> map, String url) {
        mHelper.httpPostJavaBean(type,d,map,url);
    }


    /**
     * 请求成功
     */
    public void httpSuccess(int type, String message) {

    }

    /**
     * 请求失败
     */
    public void httpFailure(int type, String error) {

    }

    public <D extends IModel> void httpJavaBeanSuccess(int type, D d) {

    }
}
