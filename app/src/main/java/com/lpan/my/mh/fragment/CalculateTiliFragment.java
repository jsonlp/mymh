package com.lpan.my.mh.fragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lpan.my.mh.R;

/**
 * Created by lpan on 2019/4/11.
 * 体活总值=50+等级*5+生活技能*4
 * 体活回复=INT(上限*0.01)+INT(等级*0.02)+2
 */
public class CalculateTiliFragment extends BaseFragment {
    private EditText mLvEt;
    private EditText mJianTiEt;
    private EditText mYangShengEt;
    private EditText mTiliCurEt;
    private EditText mHuoliliCurEt;
    private Button mButton;
    private TextView mResultTv;

    /**
     * 方寸每小时216点活力 108张FF 54000钱 5400魔
     * @return
     */


    @Override
    public int getLayoutRes() {
        return R.layout.fragment_calculate_tili;
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        mLvEt = rootView.findViewById(R.id.lv_et);
        mJianTiEt = rootView.findViewById(R.id.jian_ti_et);
        mYangShengEt = rootView.findViewById(R.id.yang_sheng_et);
        mTiliCurEt = rootView.findViewById(R.id.tili_cur_et);
        mHuoliliCurEt = rootView.findViewById(R.id.huoli_cur_et);
        mButton = rootView.findViewById(R.id.calculate_bt);
        mResultTv = rootView.findViewById(R.id.result_tv);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        });
    }

    private void calculate() {
        int lv = TextUtils.isEmpty(mLvEt.getText().toString()) ? 0 : Integer.parseInt(mLvEt.getText().toString());
        int jian_ti_lv = TextUtils.isEmpty(mJianTiEt.getText().toString()) ? 0 :Integer.parseInt(mJianTiEt.getText().toString());
        int yang_sheng_lv = TextUtils.isEmpty(mYangShengEt.getText().toString()) ? 0 :Integer.parseInt(mYangShengEt.getText().toString());
        int tili_total = 50+lv*5+jian_ti_lv*4;
        int huoli_total=50+lv*5+yang_sheng_lv*4;

        int tili_total_cur = TextUtils.isEmpty(mTiliCurEt.getText().toString()) ? 0 :Integer.parseInt(mTiliCurEt.getText().toString());
        int huoli_total_cur = TextUtils.isEmpty(mHuoliliCurEt.getText().toString()) ? 0 :Integer.parseInt(mHuoliliCurEt.getText().toString());

        int tili_total_extra = tili_total / 4;
        int huoli_total_extra = huoli_total / 4;


        int tili_per_5m = (int) (tili_total * 0.01) + (int) (lv * 0.02) + 2;
        int huoli_per_5m = (int) (huoli_total * 0.01) + (int) (lv * 0.02) + 2;

        int tili_time = (tili_total - tili_total_cur) / tili_per_5m * 5;
        int tili_time_extra = (tili_total + tili_total_extra - tili_total_cur) / tili_per_5m * 5;

        int huoli_time = (huoli_total - huoli_total_cur) / huoli_per_5m * 5;
        int huoli_time_extra = (huoli_total + huoli_total_extra - huoli_total_cur) / huoli_per_5m * 5;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("体力=");
        stringBuilder.append(tili_total);
        stringBuilder.append("(");
        stringBuilder.append(tili_total_extra);
        stringBuilder.append(")");

        stringBuilder.append(",每5分钟回复");
        stringBuilder.append(tili_per_5m);
        stringBuilder.append(",从");
        stringBuilder.append(tili_total_cur);
        stringBuilder.append("增加到");
        stringBuilder.append(tili_total);
        stringBuilder.append("需要");
        stringBuilder.append(tili_time);
        stringBuilder.append("分钟,如果储备也满的话需要");
        stringBuilder.append(tili_time_extra);
        stringBuilder.append("分钟\n");

        stringBuilder.append("活力=");
        stringBuilder.append(huoli_total);
        stringBuilder.append("(");
        stringBuilder.append(huoli_total_extra);
        stringBuilder.append(")");
        stringBuilder.append(",每5分钟回复");
        stringBuilder.append(huoli_per_5m);
        stringBuilder.append(",从");
        stringBuilder.append(huoli_total_cur);
        stringBuilder.append("增加到");
        stringBuilder.append(huoli_total);
        stringBuilder.append("需要");
        stringBuilder.append(huoli_time);
        stringBuilder.append("分钟,如果储备也满的话需要");
        stringBuilder.append(huoli_time_extra);
        stringBuilder.append("分钟\n");


        mResultTv.setText(stringBuilder.toString());


    }


}
