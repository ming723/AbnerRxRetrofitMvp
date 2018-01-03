package com.abner.ming.vipmvp.rxretrofit.listener;

import com.abner.ming.vipmvp.rxretrofit.model.IModel;

/**
 * Created by AbnerMing on 2018/1/3.
 */

public interface HelperListener {
     void httpSuccess(int type, String message);

    void httpFailure(int type, String error);

    <D extends IModel> void httpJavaBeanSuccess(int type, D d);
}
