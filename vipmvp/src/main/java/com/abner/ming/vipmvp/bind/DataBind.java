package com.abner.ming.vipmvp.bind;

import com.abner.ming.vipmvp.model.IModel;
import com.abner.ming.vipmvp.view.IDelegate;

/**
 * Created by AbnerMing on 2018/1/3.
 */

public interface DataBind<T extends IDelegate,D extends IModel> {

    void bindViewData(T t,D d);
}
