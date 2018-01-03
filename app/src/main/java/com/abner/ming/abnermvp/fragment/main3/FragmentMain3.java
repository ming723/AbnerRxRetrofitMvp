package com.abner.ming.abnermvp.fragment.main3;

import com.abner.ming.abnermvp.fragment.main1.FragmentDelegate1;
import com.abner.ming.vipmvp.presenter.FragmentPresenter;

/**
 * Created by Administrator on 2018/1/3.
 */

public class FragmentMain3 extends FragmentPresenter<FragmentDelegate3>{
    @Override
    public Class<FragmentDelegate3> getDelegateClass() {
        return FragmentDelegate3.class;
    }
}
