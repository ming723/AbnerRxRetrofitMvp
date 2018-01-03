package com.abner.ming.vipmvp.viewpager.page;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abner.ming.vipmvp.viewpager.bean.PageBean;
import com.abner.ming.vipmvp.viewpager.callback.PageHelperListener;
import com.abner.ming.vipmvp.viewpager.indicator.NormalIndicator;
import com.abner.ming.vipmvp.viewpager.indicator.TextIndicator;
import com.abner.ming.vipmvp.viewpager.indicator.TransIndicator;
import com.abner.ming.vipmvp.viewpager.indicator.ZoomIndicator;

import java.util.List;

/**
 * Created by Administrator on 2017/11/9.
 */

public class GlideViewPager extends ViewPager {
    private LayoutInflater mInflater;
    public GlideViewPager(Context context) {
        this(context,null);
    }

    public GlideViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        mInflater = LayoutInflater.from(context);
    }
    public void setPageListener(PageBean bean, int layoutid, PageHelperListener listener){
        CusViewPagerAdapter adapter = new CusViewPagerAdapter<>(bean.datas,layoutid,listener);
        setAdapter(adapter);
        setOffscreenPageLimit(3);
        setCurrentItem(0);
        if (bean.bottomLayout != null){
            //选择不同的indicator
            if (bean.bottomLayout instanceof NormalIndicator){
                ((NormalIndicator) bean.bottomLayout).addPagerData(bean,this);
            }
            if (bean.bottomLayout instanceof TransIndicator){
                ((TransIndicator) bean.bottomLayout).addPagerData(bean,this);
            }
            if (bean.bottomLayout instanceof ZoomIndicator){
                ((ZoomIndicator) bean.bottomLayout).addPagerData(bean,this);
            }
            if (bean.bottomLayout instanceof TextIndicator){
                ((TextIndicator) bean.bottomLayout).addPagerData(bean,this);
            }


        }
    }

    class CusViewPagerAdapter<T> extends PagerAdapter {
        PageHelperListener listener;
        List<T> list;
        int layoutid;
        public CusViewPagerAdapter(List<T> list,
                                   int layoutid,PageHelperListener listener) {
            this.listener = listener;
            this.list = list;
            this.layoutid = layoutid;
        }

        @Override
        public int getCount() {
            return this.list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = mInflater.inflate(layoutid,null);
            this.listener.getItemView(view, this.list.get(position));
            container.addView(view,0);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
