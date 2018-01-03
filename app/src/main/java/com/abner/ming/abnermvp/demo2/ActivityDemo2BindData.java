package com.abner.ming.abnermvp.demo2;

import com.abner.ming.vipmvp.bind.DataBind;

/**
 * Created by AbnerMing on 2018/1/3.
 */

public class ActivityDemo2BindData implements DataBind<ActivityDemo2Delegate,Demo2Bean>{
    @Override
    public void bindViewData(ActivityDemo2Delegate activityDemo2Delegate, Demo2Bean demo2Bean) {
        activityDemo2Delegate.setText(demo2Bean.getMess());
    }
}
