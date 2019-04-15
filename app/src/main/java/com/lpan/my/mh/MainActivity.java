package com.lpan.my.mh;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.lpan.my.mh.activity.BaseActivity;
import com.lpan.my.mh.fragment.HomeFragment;



public class MainActivity extends BaseActivity implements View.OnClickListener {

    private HomeFragment mHomeFragment;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViewById(R.id.tab_home).setOnClickListener(this);
        findViewById(R.id.tab_tools2).setOnClickListener(this);
        findViewById(R.id.tab_tools3).setOnClickListener(this);
        findViewById(R.id.tab_my).setOnClickListener(this);
        replaceTab(R.id.tab_home);
    }


    private void replaceTab(int tabId) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = getFragment(tabId);
        if (fragment != null) {
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commitAllowingStateLoss();
        }
    }

    private Fragment getFragment(int tabId) {
        switch (tabId) {
            case R.id.tab_home:
                if (mHomeFragment == null) {
                    mHomeFragment = HomeFragment.newInstance();
                }
                return mHomeFragment;

            case R.id.tab_tools2:
                if (mHomeFragment == null) {
                    mHomeFragment = HomeFragment.newInstance();
                }
                return mHomeFragment;

            case R.id.tab_tools3:
                if (mHomeFragment == null) {
                    mHomeFragment = HomeFragment.newInstance();
                }
                return mHomeFragment;

            case R.id.tab_my:
                if (mHomeFragment == null) {
                    mHomeFragment = HomeFragment.newInstance();
                }
                return mHomeFragment;
        }
        return null;
    }

    @Override
    public void onClick(View v) {
        replaceTab(v.getId());
    }
}
