package com.abner.ming.abnermvp.httptest;

import android.widget.TextView;

import com.abner.ming.abnermvp.R;
import com.abner.ming.vipmvp.view.AppDelegate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by AbnerMing on 2018/1/3.
 */

public class ActivityHttpTestDelegate extends AppDelegate {
    @Override
    public int getLayoutId() {
        return R.layout.activity_demo1;
    }

    @Override
    public void initWeight() {
        super.initWeight();
        TextView textView = get(R.id.tv_demo1);
        textView.setText("Hello,Abner");



    }

    public void setText(String mess){
        TextView textView = get(R.id.tv_demo1);
        textView.setText(mess);
    }

}
