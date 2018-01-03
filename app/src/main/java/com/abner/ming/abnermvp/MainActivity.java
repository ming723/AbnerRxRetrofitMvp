package com.abner.ming.abnermvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.abner.ming.abnermvp.fragment.main.FragmentMain;
import com.abner.ming.abnermvp.fragment.main1.FragmentMain1;
import com.abner.ming.abnermvp.fragment.main2.FragmentMain2;
import com.abner.ming.abnermvp.fragment.main3.FragmentMain3;
import com.abner.ming.abnermvp.fragment.main4.FragmentMain4;
import com.abner.ming.vipmvp.tab.TabView;
import com.abner.ming.vipmvp.tab.TabViewChild;
import com.abner.ming.vipmvp.view.UltimateBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    private TabView mTabView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTabView=(TabView)findViewById(R.id.tabview);
        createTabView();
        createColor();
    }

    /**
     * 设置底部button
     * */
    private void createTabView() {
        List<TabViewChild> tabViewChildList = new ArrayList<>();
        TabViewChild tabViewChild01 = new TabViewChild(R.drawable.ic_launcher, R.drawable.ic_launcher, "首页", new FragmentMain());
        TabViewChild tabViewChild02 = new TabViewChild(R.drawable.ic_launcher, R.drawable.ic_launcher, "互动", new FragmentMain1());
        TabViewChild tabViewChild03 = new TabViewChild(R.drawable.ic_launcher, R.drawable.ic_launcher, "推荐", new FragmentMain2());
        TabViewChild tabViewChild04 = new TabViewChild(R.drawable.ic_launcher, R.drawable.ic_launcher, "学习", new FragmentMain3());
        TabViewChild tabViewChild05 = new TabViewChild(R.drawable.ic_launcher, R.drawable.ic_launcher, "我的", new FragmentMain4());
        tabViewChildList.add(tabViewChild01);
        tabViewChildList.add(tabViewChild02);
        tabViewChildList.add(tabViewChild03);
        tabViewChildList.add(tabViewChild04);
        tabViewChildList.add(tabViewChild05);
        mTabView.setTabViewChild(tabViewChildList, getSupportFragmentManager());
    }

    private void createColor(){
        UltimateBar.newImmersionBuilder()
                .applyNav(true)         // 是否应用到导航栏
                .build(this)
                .apply();

    }

}
