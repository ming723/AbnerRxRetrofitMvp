package com.abner.ming.abnermvp.demo2;

import com.abner.ming.vipmvp.model.IModel;

/**
 * Created by AbnerMing on 2018/1/3.
 */

public class Demo2Bean implements IModel{
    private String mess;

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }
}
