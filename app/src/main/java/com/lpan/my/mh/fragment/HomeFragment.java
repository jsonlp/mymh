package com.lpan.my.mh.fragment;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.lpan.my.mh.R;
import com.lpan.my.mh.adapter.HomeAdapter;
import com.lpan.my.mh.model.HomeItemData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lpan on 2019/4/11.
 */
public class HomeFragment extends BaseFragment {

    private RecyclerView mRecyclerView;
    private HomeAdapter mAdapter;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        mRecyclerView = rootView.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(getAdapter());

        initData();
        Log.d("HomeFragment","initView--------dd");
    }

    private HomeAdapter getAdapter() {
        Log.d("HomeFragment","getAdapter--------22222");
        if (mAdapter == null) {
            mAdapter = new HomeAdapter(getActivity());
        }
        return mAdapter;
    }

    private void initData() {
        List<HomeItemData> list = new ArrayList<>();
        list.add(new HomeItemData(HomeItemData.CALCULATE_TILI_TAG, "1 计算体活"));
        getAdapter().add(list);
        getAdapter().notifyItemRangeChanged(0, list.size());
    }


}
