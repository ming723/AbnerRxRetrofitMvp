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

/**
 * Created by AbnerMing on 2018/1/3.
 * Activity中没有网络请求可集成此类
 */

public abstract class BaseActivity extends AppCompatActivity{
    private RelativeLayout titleLayout;
    private TextView baseTitle,titleRight;
    private ImageView baseBack;

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
        View v =layoutView(savedInstanceState);
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
        initData();
    }

    protected void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

}
