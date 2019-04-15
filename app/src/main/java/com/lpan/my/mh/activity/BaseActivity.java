package com.lpan.my.mh.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;


/**
 * Created by lpan on 2019/4/11.
 */
public abstract class BaseActivity extends FragmentActivity {

    public abstract int getLayoutRes();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        initView();
    }

    protected void initView() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
