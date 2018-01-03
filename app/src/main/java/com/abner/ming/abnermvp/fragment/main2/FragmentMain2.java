package com.abner.ming.abnermvp.fragment.main2;

import com.abner.ming.abnermvp.fragment.main1.FragmentDelegate1;
import com.abner.ming.vipmvp.presenter.FragmentPresenter;

/**
 * Created by Administrator on 2018/1/3.
 */

public class FragmentMain2 extends FragmentPresenter<FragmentDelegate2>{
    @Override
    public Class<FragmentDelegate2> getDelegateClass() {
        return FragmentDelegate2.class;
    }
}
