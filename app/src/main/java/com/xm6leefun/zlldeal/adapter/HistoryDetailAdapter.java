package com.xm6leefun.zlldeal.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xm6leefun.model.DataHistoryDetails;
import com.xm6leefun.zlldeal.R;

import java.util.List;

/**
 * Created by zll on 2017/11/10 .
 */

public   class HistoryDetailAdapter extends BaseQuickAdapter<DataHistoryDetails.RecordBean.OrderExpressBean.ExpressDataBean, BaseViewHolder> {
    String state;
    public HistoryDetailAdapter(List<DataHistoryDetails.RecordBean.OrderExpressBean.ExpressDataBean> data, String state) {
        super(R.layout.item_history_detail, data);
        this.state=state;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int positions) {
        super.onBindViewHolder(holder, positions);
        if (state.equals("-1")||state.equals("6"))
        {
            if (positions==0)
            {
                holder.getView(R.id.item_or_de_top_line).setVisibility(View.INVISIBLE);
                holder.getView(R.id.item_or_de_iv_state).setBackgroundResource(R.drawable.logis_sels_yuan);
            }else {
                holder.getView(R.id.item_or_de_top_line).setVisibility(View.VISIBLE);
                holder.getView(R.id.item_or_de_iv_state).setBackgroundResource(R.drawable.logis_nor_yuan);
            }
        }else {
            if (positions == 0)
                holder.getView(R.id.item_or_de_iv_state).setBackgroundResource(R.drawable.logis_state_ing);
            else
                holder.getView(R.id.item_or_de_iv_state).setBackgroundResource(R.drawable.logis_nor_yuan);
        }
        if ((this.getItemCount() - 1) == positions) {
            holder.getView(R.id.item_or_de_bot_line).setVisibility(View.INVISIBLE);
        } else {
            holder.getView(R.id.item_or_de_bot_line).setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void convert(BaseViewHolder helper, DataHistoryDetails.RecordBean.OrderExpressBean.ExpressDataBean item) {
//        String time = item.getCreateTimeStr().substring(item.getCreateTimeStr().indexOf(" ") + 1);
//        String day = item.getCreateTimeStr().substring(0, item.getCreateTimeStr().indexOf(" "));
        helper.setText(R.id.item_or_de_tv_day, item.getFtime());
        helper.setText(R.id.item_or_de_tv_time, item.getTime());
        helper.setText(R.id.item_or_de_tv_state, item.getContext());
        helper.setText(R.id.item_or_de_tv_remark, item.getLocation());
//        helper.setText(R.id.item_or_de_tv_day, "2018-02-06");
//        helper.setText(R.id.item_or_de_tv_time, "15:36:26");
//        helper.setText(R.id.item_or_de_tv_state, "你好");
//        helper.setText(R.id.item_or_de_tv_remark, "到达厦门");


//        String time = item.getCreateTimeStr().substring(item.getCreateTimeStr().indexOf(" ") + 1);
//        String day = item.getCreateTimeStr().substring(0, item.getCreateTimeStr().indexOf(" "));
//        helper.setText(R.id.item_or_de_tv_day, day);
//        helper.setText(R.id.item_or_de_tv_time, time);
//        helper.setText(R.id.item_or_de_tv_state, item.getStateName());
//        helper.setText(R.id.item_or_de_tv_remark, item.getDetails());
    }
}
