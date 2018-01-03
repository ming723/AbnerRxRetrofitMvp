package com.abner.ming.abnermvp.fragment.main4;

import com.abner.ming.abnermvp.fragment.main1.FragmentDelegate1;
import com.abner.ming.vipmvp.presenter.FragmentPresenter;

/**
 * Created by Administrator on 2018/1/3.
 */

public class FragmentMain4 extends FragmentPresenter<FragmentDelegate4>{
    @Override
    public Class<FragmentDelegate4> getDelegateClass() {
        return FragmentDelegate4.class;
    }
}
