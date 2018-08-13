package com.xm6leefun.zlldeal.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xm6leefun.model.DataHistory;
import com.xm6leefun.zlldeal.R;

import java.util.List;

/**
 * Created by Administrator on 2017/11/8 0008.
 */

public class HistoryMainAdapter extends BaseQuickAdapter<DataHistory.RecordBean.TaskListBean, BaseViewHolder> {

    public HistoryMainAdapter(List<DataHistory.RecordBean.TaskListBean> data) {
        super(R.layout.item_history_main, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, DataHistory.RecordBean.TaskListBean item) {

//        ImageView ivbac =  helper.getView(R.id.item_iv_bank_bac);
//        Glide.with(AppManager.getAppManager().currentActivity()).load(item.get()).into((ImageView) helper.getView(R.id.item_bank_iv_logo));

        helper.setText(R.id.item_hm_tv_time, item.getCreateTimeStr());
        helper.setText(R.id.item_hm_tv_total, item.getSumNum());
        helper.setText(R.id.item_hm_tv_wancheng, item.getFinishNum());
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
