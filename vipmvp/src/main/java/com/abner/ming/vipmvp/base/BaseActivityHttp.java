package com.abner.ming.vipmvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.abner.ming.vipmvp.R;
import com.abner.ming.vipmvp.rxretrofit.helper.Helper;
import com.abner.ming.vipmvp.rxretrofit.listener.HelperListener;
import com.abner.ming.vipmvp.rxretrofit.model.IModel;
import com.abner.ming.vipmvp.rxretrofit.utils.HttpHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by AbnerMing on 2018/1/3.
 * Activity中有网络请求可集成此类
 */

public abstract class BaseActivityHttp extends AppCompatActivity implements HelperListener {
    private RelativeLayout titleLayout;
    private TextView baseTitle, titleRight;
    private ImageView baseBack;
    private HttpHelper mHttpHelper;
    private Helper mHelper;

    protected abstract boolean showBack();

    protected abstract String baseTitle();

    protected abstract View layoutView(Bundle savedInstanceState);

    protected abstract void initData();

    /**
     * 设置标题
     */
    protected void setTitle(String title) {
        baseTitle.setText(title);
    }

    protected void setShowTitle(boolean isShow) {
        if (isShow) {
            titleLayout.setVisibility(View.GONE);
        } else {
            titleLayout.setVisibility(View.VISIBLE);
        }
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        LinearLayout baseView = (LinearLayout) findViewById(R.id.base_view);
        titleLayout = (RelativeLayout) findViewById(R.id.base_layout_title);
        baseTitle = (TextView) findViewById(R.id.base_title);
        baseBack = (ImageView) findViewById(R.id.base_view_back);
        titleRight = (TextView) findViewById(R.id.base_title_right);
        View v = layoutView(savedInstanceState);
        baseView.addView(v);
        if (!TextUtils.isEmpty(baseTitle())) {
            baseTitle.setText(baseTitle());
        }
        if (showBack()) {
            baseBack.setVisibility(View.VISIBLE);
        } else {
            baseBack.setVisibility(View.GONE);
        }

        baseBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mHttpHelper = new HttpHelper(headerMap, this);
        mHelper=new Helper(mHttpHelper,this);
        initData();
    }

    protected void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
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
     * 是否显示加载框
     */
    public void isCache(boolean cache) {
        mHttpHelper.setCache(cache);
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
