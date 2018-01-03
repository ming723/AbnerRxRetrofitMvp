package com.abner.ming.vipmvp.listview;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abner.ming.vipmvp.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

/**
 * Created by zhengjin on 17/3/20.
 */

public class ViewHoder {
    private SparseArray<View> mView;
    private int mPosition;
    private View mConvertView;
    private Context mContext;

    public View getmConvertView() {
        return mConvertView;
    }

    public ViewHoder(Context context, int position, ViewGroup parent, int layoutId) {
        mPosition = position;
        mContext = context;
        mView = new SparseArray<View>();
        mConvertView = View.inflate(context, layoutId, null);
        mConvertView.setTag(this);
    }

    public static ViewHoder getViewHoder(Context context, int position,
                                         View convertView, ViewGroup parent, int layoutId) {
        if (convertView == null) {
            return new ViewHoder(context, position, parent, layoutId);
        } else {
            ViewHoder viewHoder = (ViewHoder) convertView.getTag();
            viewHoder.mPosition = position;
            return viewHoder;
        }
    }

    //通过ViewHoder获取控件
    public <T extends View> T getView(int layoutId) {
        View view = mView.get(layoutId);
        if (view == null) {
            view = getmConvertView().findViewById(layoutId);
            mView.put(layoutId, view);
        }
        return (T) view;
    }

    //TextView设置数据
    public ViewHoder setText(int viewId, String txt) {
        TextView mTextView = getView(viewId);
        mTextView.setText(txt);
        return this;
    }

    public ViewHoder setPic(int viewId, String url) {
        ImageView mImageView = getView(viewId);
        //ImageLoader.getInstance().displayImage(url, mImageView);
        Picasso.with(mContext).load(url).fit().error(R.drawable.ic_launcher).placeholder(mImageView.getDrawable()).memoryPolicy(MemoryPolicy.NO_CACHE).into(mImageView);
        return this;
    }
}
