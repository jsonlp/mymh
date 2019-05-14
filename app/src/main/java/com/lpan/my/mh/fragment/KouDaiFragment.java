package com.lpan.my.mh.fragment;

import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.lpan.my.mh.R;
import com.lpan.my.mh.model.GameMoneyModel;
import com.lpan.my.mh.model.LingShiModel;
import com.lpan.my.mh.model.QiangHuaModle;
import com.lpan.my.mh.utils.Preferences;
import com.lpan.my.mh.utils.Utils;

/**
 * Created by lpan on 2019/5/13.
 */
public class KouDaiFragment extends BaseFragment implements View.OnClickListener {

    private EditText cbg_price_edit;
    private EditText game_time_price_edit;
    private EditText qiang_hua_cost_edit;
    private EditText qiang_hua_price_edit;
    private EditText ling_shi_cost_edit;
    private EditText ling_shi_price_edit;
    private Button calculate_qiang_hua_bt;
    private Button calculate_ling_shi_bt;

    private GameMoneyModel mGameMoneyModel;
    private LingShiModel mLingShiModel;
    private QiangHuaModle mQiangHuaModle;
    private Gson mGson = new Gson();


    @Override
    public int getLayoutRes() {
        return R.layout.fragment_koudai;
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        cbg_price_edit = rootView.findViewById(R.id.cbg_price_edit);
        game_time_price_edit = rootView.findViewById(R.id.game_time_price_edit);
        qiang_hua_cost_edit = rootView.findViewById(R.id.qiang_hua_cost_edit);
        qiang_hua_price_edit = rootView.findViewById(R.id.qiang_hua_price_edit);
        ling_shi_cost_edit = rootView.findViewById(R.id.ling_shi_cost_edit);
        ling_shi_price_edit = rootView.findViewById(R.id.ling_shi_price_edit);
        calculate_qiang_hua_bt = rootView.findViewById(R.id.calculate_qiang_hua_bt);
        calculate_ling_shi_bt = rootView.findViewById(R.id.calculate_ling_shi_bt);

        calculate_qiang_hua_bt.setOnClickListener(this);
        calculate_ling_shi_bt.setOnClickListener(this);

        initData();

        cbg_price_edit.setText(getGameMoneyModel().getCbgPrece()+"");
        game_time_price_edit.setText(getGameMoneyModel().getPointCardThousand()+"");
        qiang_hua_cost_edit.setText(getQiangHuaModle().getCost()+"");
        qiang_hua_price_edit.setText(getQiangHuaModle().getPrice()+"");
        ling_shi_cost_edit.setText(getLingShiModel().getCost()+"");
        ling_shi_price_edit.setText(getLingShiModel().getJingshi()+"/"+
                getLingShiModel().getPeishi()+"/"+
                getLingShiModel().getShouzhuo()+"/"+
                getLingShiModel().getErshi()+"/"+
                getLingShiModel().getJiezhi());

    }

    private void initData(){
        String gamemoney = Preferences.getInstance().getString(Preferences.KEY_GAME_MONEY_INFO);
        String lingshi = Preferences.getInstance().getString(Preferences.KEY_LING_SHI_INFO);
        String qianghua = Preferences.getInstance().getString(Preferences.KEY_QIANG_HUA_INFO);

        if (!TextUtils.isEmpty(gamemoney)) {
            mGameMoneyModel = mGson.fromJson(gamemoney, GameMoneyModel.class);
        }
        if (!TextUtils.isEmpty(lingshi)) {
            mLingShiModel = mGson.fromJson(lingshi, LingShiModel.class);
        }
        if (!TextUtils.isEmpty(qianghua)) {
            mQiangHuaModle = mGson.fromJson(qianghua, QiangHuaModle.class);
        }
    }

    private GameMoneyModel getGameMoneyModel(){
        if (mGameMoneyModel == null) {
            mGameMoneyModel = new GameMoneyModel();
        }
        return mGameMoneyModel;
    }

    private LingShiModel getLingShiModel(){
        if (mLingShiModel == null) {
            mLingShiModel = new LingShiModel();
        }
        return mLingShiModel;
    }

    private QiangHuaModle getQiangHuaModle(){
        if (mQiangHuaModle == null) {
            mQiangHuaModle = new QiangHuaModle();
        }
        return mQiangHuaModle;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.calculate_qiang_hua_bt:
                calculateQiangHua();
                break;

            case R.id.calculate_ling_shi_bt:
                calculateLingShi();
                break;
        }
    }

    private void calculateQiangHua() {
        double qiangHuaCost = Double.parseDouble(qiang_hua_cost_edit.getText().toString());
        double qiangHuaPrice = Double.parseDouble(qiang_hua_price_edit.getText().toString());
        double gameTimePrice = Double.parseDouble(game_time_price_edit.getText().toString());
        double cbgPrice = Double.parseDouble(cbg_price_edit.getText().toString());

        double times = Utils.keepTwoDecimal(1000 * 100 / qiangHuaCost / 100.0d);
        double total = Utils.keepTwoDecimal(times * qiangHuaPrice * 30.0d);
        double cbgPricePer = cbgPrice * 100 / 3000 / 100.0d;
        double getRMB = Utils.keepTwoDecimal(total * cbgPricePer * 0.95f);

        getGameMoneyModel().setCbgPrece(cbgPrice);
        getGameMoneyModel().setPointCardThousand(gameTimePrice);
        getQiangHuaModle().setPrice(qiangHuaPrice);
        getQiangHuaModle().setCost(qiangHuaCost);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("1000点卡需要");
        stringBuilder.append(gameTimePrice);
        stringBuilder.append("元,");
        stringBuilder.append("可以做强化任务");
        stringBuilder.append(times);
        stringBuilder.append("次,");
        stringBuilder.append("可获得MHB:");
        stringBuilder.append(total);
        stringBuilder.append(",通过CBG卖出,扣除手续费后可得:");
        stringBuilder.append(getRMB);
        stringBuilder.append("元,");
        if (getRMB > gameTimePrice) {
            stringBuilder.append("可赚:");
            stringBuilder.append(Utils.keepTwoDecimal(getRMB - gameTimePrice));
            stringBuilder.append("元!");
        } else if (getRMB < gameTimePrice) {
            stringBuilder.append("赔了:");
            stringBuilder.append(Utils.keepTwoDecimal(gameTimePrice - getRMB));
            stringBuilder.append("元!");
        } else {
            stringBuilder.append("不赚不赔,白嫖经验啦!");
        }
        showDialog(stringBuilder.toString());
        saveData();
    }

    private void calculateLingShi() {
        String lingshiPrice = ling_shi_price_edit.getText().toString();
        String[] split = lingshiPrice.split("/");
        int jingshi = Integer.parseInt(split[0]);
        int shouzuo = Integer.parseInt(split[1]);
        int peishi = Integer.parseInt(split[2]);
        int ershi = Integer.parseInt(split[3]);
        int jiezhi = Integer.parseInt(split[4]);

        double lingshiCost = Double.parseDouble(ling_shi_cost_edit.getText().toString());
        double lingshiPriceAv = jingshi + (shouzuo + peishi + ershi + jiezhi)/4;
        double gameTimePrice = Double.parseDouble(game_time_price_edit.getText().toString());
        double cbgPrice = Double.parseDouble(cbg_price_edit.getText().toString());


        getLingShiModel().setJingshi(jingshi);
        getLingShiModel().setShouzhuo(shouzuo);
        getLingShiModel().setPeishi(peishi);
        getLingShiModel().setErshi(ershi);
        getLingShiModel().setJiezhi(jiezhi);
        getLingShiModel().setCost(lingshiCost);
        getGameMoneyModel().setCbgPrece(cbgPrice);
        getGameMoneyModel().setPointCardThousand(gameTimePrice);

        double times = Utils.keepTwoDecimal(1000 * 100 / lingshiCost / 100.0d);
        double total = Utils.keepTwoDecimal(times * lingshiPriceAv * 1.0d);
        double cbgPricePer = cbgPrice * 100 / 3000 / 100.0d;
        double getRMB = Utils.keepTwoDecimal(total * cbgPricePer * 0.95f);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("1000点卡需要");
        stringBuilder.append(gameTimePrice);
        stringBuilder.append("元,");
        stringBuilder.append("可以做80灵饰任务");
        stringBuilder.append(times);
        stringBuilder.append("次,");
        stringBuilder.append("可获得MHB:");
        stringBuilder.append(total);
        stringBuilder.append(",通过CBG卖出,扣除手续费后可得:");
        stringBuilder.append(getRMB);
        stringBuilder.append("元,");
        if (getRMB > gameTimePrice) {
            stringBuilder.append("可赚:");
            stringBuilder.append(Utils.keepTwoDecimal(getRMB - gameTimePrice));
            stringBuilder.append("元!");
        } else if (getRMB < gameTimePrice) {
            stringBuilder.append("赔了:");
            stringBuilder.append(Utils.keepTwoDecimal(gameTimePrice - getRMB));
            stringBuilder.append("元!");
        } else {
            stringBuilder.append("不赚不赔,白嫖经验啦!");
        }
        showDialog(stringBuilder.toString());
        saveData();
    }

    private void showDialog(String message) {
        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setTitle("结果如下:")
                .setMessage(message)
                .setPositiveButton("ok", null)
                .create();
        dialog.show();
    }


    private void saveData(){
        String gameMoney = mGson.toJson(getGameMoneyModel());
        String lingshi = mGson.toJson(getLingShiModel());
        String qianghua = mGson.toJson(getQiangHuaModle());

        Preferences.getInstance().putString(Preferences.KEY_GAME_MONEY_INFO,gameMoney);
        Preferences.getInstance().putString(Preferences.KEY_LING_SHI_INFO,lingshi);
        Preferences.getInstance().putString(Preferences.KEY_QIANG_HUA_INFO,qianghua);

    }














}
