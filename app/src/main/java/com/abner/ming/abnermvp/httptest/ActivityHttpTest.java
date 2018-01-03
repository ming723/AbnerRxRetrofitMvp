package com.abner.ming.abnermvp.httptest;

import android.view.View;

import com.abner.ming.abnermvp.R;
import com.abner.ming.vipmvp.presenter.ActivityPresenter;
import com.abner.ming.vipmvp.rxretrofit.helper.Helper;
import com.abner.ming.vipmvp.rxretrofit.listener.HelperListener;
import com.abner.ming.vipmvp.rxretrofit.model.IModel;
import com.abner.ming.vipmvp.rxretrofit.utils.HttpHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by AbnerMing on 2018/1/3.
 * 网络请求
 */

public class ActivityHttpTest extends ActivityPresenter<ActivityHttpTestDelegate> implements View.OnClickListener {
    @Override
    public Class<ActivityHttpTestDelegate> getDelegateClass() {
        return ActivityHttpTestDelegate.class;
    }

    @Override
    protected boolean showBack() {
        return true;
    }

    @Override
    protected String baseTitle() {
        return "ActivityHttpTest";
    }

    @Override
    public void bindEnvsListener() {
        super.bindEnvsListener();
        isHttpShow(true);
        isCache(true);
        delegate.setOnClickListener(this, R.id.btn_demo1);
    }
    /**
     * 返回字符串信息
     * */
    @Override
    public void httpSuccess(int type, String message) {
        super.httpSuccess(type, message);
        delegate.setText(message);
    }
    /**
     * 返回错误信息
     * */
    @Override
    public void httpFailure(int type, String error) {
        super.httpFailure(type, error);
        delegate.setText(error);
    }

    /**
     * 返回JavaBean信息
     * */
    @Override
    public <D extends IModel> void httpJavaBeanSuccess(int type, D d) {
        super.httpJavaBeanSuccess(type, d);
        TestBean testBean = (TestBean) d;
        List<TestBean.Subjects> subjects = testBean.getSubjects();
        StringBuffer sb = new StringBuffer();
        for (TestBean.Subjects bean : subjects) {
            sb.append(bean.getTitle()).append(",");
        }
        delegate.setText(sb.toString());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_demo1:
                Map<String, String> map = new HashMap<>();
                map.put("start", "0");
                map.put("count", "10");
                //httpGetString(0,map,"top250");
                httpGetJavaBean(0, new TestBean(), map, "top250");
                break;
        }
    }

    /**
     * 单独使用网络请求
     * */

    private void doGetString(){
        Map<String, String> map = new HashMap<>();
        map.put("start", "0");
        map.put("count", "10");
        HttpHelper mHttpHelper = new HttpHelper(map, this);
        new Helper(mHttpHelper,new HelperListener(){
            @Override
            public void httpSuccess(int type, String message) {

            }

            @Override
            public void httpFailure(int type, String error) {

            }

            @Override
            public <D extends IModel> void httpJavaBeanSuccess(int type, D d) {

            }
        }).httpGetString(0,map,"top250");
    }
}
