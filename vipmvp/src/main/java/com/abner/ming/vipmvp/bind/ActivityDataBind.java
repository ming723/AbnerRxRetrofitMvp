package com.abner.ming.vipmvp.bind;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.abner.ming.vipmvp.model.IModel;
import com.abner.ming.vipmvp.presenter.ActivityPresenter;
import com.abner.ming.vipmvp.view.IDelegate;
/**
 * Created by AbnerMing on 2018/1/3.
 * Activity数据绑定
 */

public abstract class ActivityDataBind<T extends IDelegate> extends ActivityPresenter<T>{
    private DataBind dataBind;

    public abstract DataBind getDataBind();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBind=getDataBind();
    }

    public <D extends IModel> void notifyChangeData(D data){
        if(dataBind!=null){
            dataBind.bindViewData(delegate,data);
        }
    }
}
