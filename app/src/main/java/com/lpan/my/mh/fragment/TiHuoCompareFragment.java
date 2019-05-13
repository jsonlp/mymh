package com.lpan.my.mh.fragment;

import android.view.View;
import android.widget.TextView;

import com.lpan.my.mh.R;

/**
 * Created by lpan on 2019/4/23.
 */
public class TiHuoCompareFragment  extends BaseFragment{

   private TextView mDescTv;

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_ti_huo_compare;
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        mDescTv = rootView.findViewById(R.id.desc_tv);
        mDescTv.setText(getString());
    }

    private String getString(){
        return "hahh";
    }
}
