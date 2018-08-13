package com.xm6leefun.zlldeal.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xm6leefun.model.DataHisDetailsList;
import com.xm6leefun.zlldeal.R;

import java.util.List;

/**
 * Created by Administrator on 2017/11/8 0008.
 */

public class HistoryAdapter extends BaseQuickAdapter<DataHisDetailsList.RecordBean.TaskListBean, BaseViewHolder> {

    public HistoryAdapter(List<DataHisDetailsList.RecordBean.TaskListBean> data) {
        super(R.layout.item_history, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, DataHisDetailsList.RecordBean.TaskListBean item) {

//        ImageView ivbac =  helper.getView(R.id.item_iv_bank_bac);
//        Glide.with(AppManager.getAppManager().currentActivity()).load(item.get()).into((ImageView) helper.getView(R.id.item_bank_iv_logo));
//        Log.e("getOrderNum",item.getOrderNum());
        helper.setText(R.id.item_his_tv_order_num, item.getOrderNum());
        helper.setText(R.id.item_his_tv_order_goods, item.getGoodsName());
        helper.setText(R.id.item_his_tv_order_addr, item.getRegion());
//        helper.setText(R.id.item_his_tv_order_num, item.getOrderNum());
//        helper.setText(R.id.item_mfl_tv_clear_type, item.getClear_type());
//
//        helper.setText(R.id.item_mfl_tv_feilv, item.getRate());
//        helper.setText(R.id.item_mfl_tv_jiesuan, item.getHand_charge());
//        helper.setText(R.id.item_mfl_tv_danbi, item.getSingel_limit());
//        helper.setText(R.id.item_mfl_tv_dantian, item.getDay_limit());
//        helper.setText(R.id.item_company_tv_time, DateUtils.timesTwo(item.getCreateTime())+"");
//        GetImageByUrl getImageByUrl = new GetImageByUrl();
//        getImageByUrl.setImage((ImageView)helper.getView(R.id.item_company_iv_pic), item.getImage());
    }
}
