package com.abner.ming.vipmvp.view;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by AbnerMing on 2018/1/3.
 */

public abstract class AppDelegate implements IDelegate {
    private View rootView;

    private SparseArray<View> views=new SparseArray<>();

    public abstract int getLayoutId();

    @Override
    public void initWeight() {

    }

    @Override
    public View getRootView() {
        return rootView;
    }

    public <T extends View> T get(int id){
        T view=(T)views.get(id);
        if(view==null){
            view=(T)rootView.findViewById(id);
            views.put(id,view);
        }
        return view;
    }

    public void setOnClickListener(View.OnClickListener listener,int... ids){
        if(ids==null){
            return;
        }
        for(int id:ids){
            get(id).setOnClickListener(listener);
        }
    }

    @Override
    public void create(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        rootView = inflater.inflate(getLayoutId(), viewGroup, false);
    }
}
