package com.lpan.my.mh.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lpan.my.mh.R;
import com.lpan.my.mh.fragment.CalculateTiliFragment;
import com.lpan.my.mh.model.HomeItemData;
import com.lpan.my.mh.utils.FragmentUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lpan on 2019/4/11.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {

    private List<HomeItemData> mList;

    private Context mContext;

    public HomeAdapter(Context context) {
        mList = new ArrayList<>();
        mContext =context;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_home, null, false);
        inflate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentUtils.navigateToInNewActivity(mContext, new CalculateTiliFragment(),null);
            }
        });
        return new HomeViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder homeViewHolder, int i) {
        HomeItemData homeItemData = mList.get(i);
        homeViewHolder.mTextView.setText(homeItemData.getTitle());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public void add(List<HomeItemData> list) {
        mList.addAll(list);
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.title_tv);
        }
    }
}
