package com.abner.ming.abnermvp.demo1;

import android.view.View;

import com.abner.ming.abnermvp.R;
import com.abner.ming.vipmvp.presenter.ActivityPresenter;

/**
 * Created by AbnerMing on 2018/1/3.
 */

public class ActivityDemo1 extends ActivityPresenter<ActivityDemo1Delegate> implements View.OnClickListener {
    @Override
    public Class<ActivityDemo1Delegate> getDelegateClass() {
        return ActivityDemo1Delegate.class;
    }

    @Override
    public void bindEnvsListener() {
        super.bindEnvsListener();
        delegate.setOnClickListener(this,R.id.btn_demo1);
    }

    @Override
    protected boolean showBack() {
        return true;
    }

    @Override
    protected String baseTitle() {
        return "ActivityDemo1";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_demo1:
                delegate.setText("AbnerMingggggg");
                break;
        }
    }
}
