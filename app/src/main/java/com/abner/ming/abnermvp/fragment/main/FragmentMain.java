package com.abner.ming.abnermvp.fragment.main;

import android.content.Intent;
import android.view.View;

import com.abner.ming.abnermvp.R;
import com.abner.ming.abnermvp.demo1.ActivityDemo1;
import com.abner.ming.abnermvp.demo2.ActivityDemo2;
import com.abner.ming.abnermvp.httptest.ActivityHttpTest;
import com.abner.ming.vipmvp.dialog.StyledDialog;
import com.abner.ming.vipmvp.dialog.interfaces.MyDialogListener;
import com.abner.ming.vipmvp.presenter.FragmentPresenter;

/**
 * Created by Administrator on 2018/1/3.
 */

public class FragmentMain extends FragmentPresenter<FragmentDelegate> implements View.OnClickListener {
    @Override
    public Class<FragmentDelegate> getDelegateClass() {
        return FragmentDelegate.class;
    }

    @Override
    public void bindEnvsListener() {
        super.bindEnvsListener();
        delegate.setOnClickListener(this,R.id.btn_demo1);
        delegate.setOnClickListener(this,R.id.btn_demo2);
        delegate.setOnClickListener(this,R.id.btn_demo3);
        delegate.setOnClickListener(this,R.id.btn_demo4);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_demo1:
                getActivity().startActivity(new Intent(getActivity(), ActivityDemo1.class));
                break;
            case R.id.btn_demo2:
                getActivity().startActivity(new Intent(getActivity(), ActivityDemo2.class));
                break;
            case R.id.btn_demo3:
                getActivity().startActivity(new Intent(getActivity(), ActivityHttpTest.class));
                break;
            case R.id.btn_demo4:
                StyledDialog.buildIosAlert(getContext(),"重要通知", "88888", new MyDialogListener() {
                    @Override
                    public void onFirst() {
                        toast("onFirst");
                    }

                    @Override
                    public void onSecond() {
                        toast("onSecond");
                    }
                }).show();
                break;
        }
    }
}
