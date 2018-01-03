package com.abner.ming.abnermvp.fragment.main4;

import android.widget.TextView;

import com.abner.ming.abnermvp.R;
import com.abner.ming.vipmvp.view.AppDelegate;

/**
 * Created by Administrator on 2018/1/3.
 */

public class FragmentDelegate4 extends AppDelegate {
    @Override
    public int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    public void initWeight() {
        super.initWeight();
        TextView textView = get(R.id.tv_demo1);
        textView.setText("FragmentDelegate4");
    }
}
