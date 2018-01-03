package com.abner.ming.vipmvp.bind;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.abner.ming.vipmvp.model.IModel;
import com.abner.ming.vipmvp.presenter.FragmentPresenter;
import com.abner.ming.vipmvp.view.IDelegate;

/**
 * Created by AbnerMing on 2018/1/3.
 * Fragment数据绑定
 */

public abstract class FragMentDataBind<T extends IDelegate> extends FragmentPresenter<T>{
    private DataBind dataBind;

    public abstract DataBind getDataBind();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dataBind=getDataBind();
    }
    public <D extends IModel> void notifyChangeData(D data){
        if(dataBind!=null){
            dataBind.bindViewData(delegate,data);
        }
    }
}
