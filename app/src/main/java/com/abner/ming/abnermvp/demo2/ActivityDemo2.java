package com.abner.ming.abnermvp.demo2;

import android.view.View;

import com.abner.ming.abnermvp.R;
import com.abner.ming.vipmvp.bind.ActivityDataBind;
import com.abner.ming.vipmvp.bind.DataBind;

/**
 * Created by AbnerMing on 2018/1/3.
 */

public class ActivityDemo2 extends ActivityDataBind<ActivityDemo2Delegate> implements View.OnClickListener {
    @Override
    public DataBind getDataBind() {
        return new ActivityDemo2BindData();
    }

    @Override
    public Class<ActivityDemo2Delegate> getDelegateClass() {
        return ActivityDemo2Delegate.class;
    }

    @Override
    public void bindEnvsListener() {
        super.bindEnvsListener();
        delegate.setOnClickListener(this, R.id.btn_demo1);
    }

    @Override
    protected boolean showBack() {
        return true;
    }

    @Override
    protected String baseTitle() {
        return "ActivityDemo2";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_demo1:
                Demo2Bean bean=new Demo2Bean();
                bean.setMess("666666");
                notifyChangeData(bean);
                break;
        }
    }
}
