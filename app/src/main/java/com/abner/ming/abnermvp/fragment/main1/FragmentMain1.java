package com.abner.ming.abnermvp.fragment.main1;

import com.abner.ming.abnermvp.fragment.main.FragmentDelegate;
import com.abner.ming.vipmvp.presenter.FragmentPresenter;

/**
 * Created by Administrator on 2018/1/3.
 */

public class FragmentMain1 extends FragmentPresenter<FragmentDelegate1>{
    @Override
    public Class<FragmentDelegate1> getDelegateClass() {
        return FragmentDelegate1.class;
    }
}
