package com.lpan.my.mh.activity;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.lpan.my.mh.Constants;
import com.lpan.my.mh.R;
import com.lpan.my.mh.utils.FragmentUtils;


/**
 * Created by lpan on 2016/12/19.
 */

public class TransparentActivity extends BaseActivity {


    @Override
    public int getLayoutRes() {
        return R.layout.activity_goal;
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String className = getIntent().getExtras().getString(Constants.EXTRAS_CLASS_NAME);
        Bundle activityBundle = getIntent().getBundleExtra(Constants.EXTRAS_BUNDLE);

        loadFragment(className, activityBundle);
    }

    protected void loadFragment(String className, Bundle bundle) {
        if (!TextUtils.isEmpty(className)) {
            Fragment fragment = null;
            try {
                fragment = (Fragment) Class.forName(className).newInstance();
                FragmentUtils.replaceFragment(R.id.container, getSupportFragmentManager(),
                        fragment, bundle);

            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            } else {
            }
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public void finishAfterTransition() {
        super.finishAfterTransition();
    }
}
