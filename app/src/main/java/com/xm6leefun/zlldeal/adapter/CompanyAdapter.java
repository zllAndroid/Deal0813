package com.xm6leefun.zlldeal.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xm6leefun.zlldeal.R;

import java.util.List;

/**
 * Created by Administrator on 2017/11/8 0008.
 */

public class CompanyAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    Context context;
    public CompanyAdapter(Context context, List<String> data) {
        super(R.layout.item_company, data);
        this.context=context;
    }
    @Override
    protected void convert(BaseViewHolder helper,String item) {

        ImageView ivbac =  helper.getView(R.id.item_new_iv_img);
        Glide.with(context).load(R.drawable.test_goods).into((ImageView) helper.getView(R.id.item_new_iv_img));
        helper.setText(R.id.item_new_tv_name, "老白干");
//        ImageView ivbac =  helper.getView(R.id.item_new_iv_img);
//        if (item.getImg()!=0)
//        Glide.with(context).load(item.getImg()).into((ImageView) helper.getView(R.id.item_new_iv_img));
////        Log.e("getOrderNum",item.getOrderNum());
//        if (!StrUtils.isEmpty(item.getTital()))
//        helper.setText(R.id.item_new_tv_name, item.getTital());
//        helper.setText(R.id.item_his_tv_order_num, item.getOrderNum());
//        helper.setText(R.id.item_mfl_tv_clear_type, item.getClear_type());
    }
}
