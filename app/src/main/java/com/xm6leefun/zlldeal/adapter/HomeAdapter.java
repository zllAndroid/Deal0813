package com.xm6leefun.zlldeal.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xm6leefun.model.DataHome;
import com.xm6leefun.model.DataHomeList;
import com.xm6leefun.zlldeal.R;
import com.xm6leefun.zlldeal.base.AppManager;

import java.util.List;

/**
 * Created by Administrator on 2017/11/8 0008.
 */

public class HomeAdapter extends BaseQuickAdapter<DataHomeList.RecordBean.TicketListBean, BaseViewHolder> {

    public HomeAdapter(List<DataHomeList.RecordBean.TicketListBean> data) {
        super(R.layout.item_home, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, DataHomeList.RecordBean.TicketListBean item) {

        ImageView ivbac =  helper.getView(R.id.item_iv_img);
        Glide.with(AppManager.getAppManager().currentActivity()).load(item.getStockImg()).error(AppManager.getAppManager().currentActivity().getResources().getDrawable(R.mipmap.ic_launcher)).into(ivbac);

//        helper.setText(R.id.item_mfl_tv_tital, item.getTitle());
//        helper.setText(R.id.item_mfl_tv_clear_type, item.getClear_type());
//
        helper.setText(R.id.item_tv_name, item.getStockName());
//        helper.setText(R.id.item_iv_img, item.getStockName());
//        helper.setText(R.id.item_home_tv_order_goods, item.getGoodsName());
//        helper.setText(R.id.item_home_tv_order_addr, item.getRegion());
//        helper.setText(R.id.item_home_tv_order_state, item.getOrderStateStr());
//        helper.setText(R.id.item_mfl_tv_jiesuan, item.getHand_charge());
//        helper.setText(R.id.item_mfl_tv_danbi, item.getSingel_limit());
//        helper.setText(R.id.item_mfl_tv_dantian, item.getDay_limit());
//        helper.setText(R.id.item_company_tv_time, DateUtils.timesTwo(item.getCreateTime())+"");
//        GetImageByUrl getImageByUrl = new GetImageByUrl();
//        getImageByUrl.setImage((ImageView)helper.getView(R.id.item_company_iv_pic), item.getImage());
    }
}
