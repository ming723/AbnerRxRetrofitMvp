package com.abner.ming.vipmvp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by AbnerMing on 2018/1/3.
 */

public interface IDelegate {

    void initWeight();

    View getRootView();

    void create(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle);
}
