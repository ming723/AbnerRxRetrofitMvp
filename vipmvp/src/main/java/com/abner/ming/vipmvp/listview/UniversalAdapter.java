package com.abner.ming.vipmvp.listview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by zhengjin on 17/3/20.
 */

public abstract class UniversalAdapter<T> extends BaseAdapter {
    protected List<T> mDatas;
    protected Context context;
    private int layoutId;

    public UniversalAdapter(Context context, List<T> mDatas, int layoutId) {
        this.mDatas = mDatas;
        this.context = context;
        this.layoutId = layoutId;
    }

    @Override
    public int getCount() {
        if (mDatas == null) {
            return 0;
        }
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoder viewHoder = ViewHoder.getViewHoder(context, position, convertView, parent, layoutId);
        getPosition(position);
        convertItem(viewHoder,position);
        convert(viewHoder, mDatas.get(position));
        return viewHoder.getmConvertView();
    }

    protected abstract void convert(ViewHoder viewHoder, Object item);
    protected  void convertItem(ViewHoder viewHoder, int position){

    }

    public void setList(List<T> mDatas) {
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    protected int getPosition(int position) {
        return position;
    }

}
