package com.abner.ming.abnermvp.demo1;

import android.widget.TextView;

import com.abner.ming.abnermvp.R;
import com.abner.ming.vipmvp.view.AppDelegate;

/**
 * Created by AbnerMing on 2018/1/3.
 */

public class ActivityDemo1Delegate extends AppDelegate {
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
