package com.xm6leefun.zlldeal.main_code.home_order;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.xm6leefun.model.DataHistoryDetails;
import com.xm6leefun.zlldeal.R;
import com.xm6leefun.zlldeal.adapter.HistoryDetailAdapter;
import com.xm6leefun.zlldeal.base.AppAllKey;
import com.xm6leefun.zlldeal.base.CommonParameter;
import com.xm6leefun.zlldeal.base.MyBaseActivity;
import com.xm6leefun.zlldeal.base.URL_GET;
import com.xm6leefun.zlldeal.utils.HelpUtils;
import com.xm6leefun.zlldeal.utils.NetWorkUtlis;
import com.xm6leefun.zlldeal.utils.SPUtils;
import com.xm6leefun.zlldeal.utils.StrUtils;

import java.util.List;

import butterknife.BindView;

//商户入驻
public class HistoryDetailsActivity extends MyBaseActivity {

    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    //    @BindView(R.id.include_top_lin_back)
//    LinearLayout includeTopLinBack;
    @BindView(R.id.his_details_recyc)
    RecyclerView mRecyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_history_details);
//        ButterKnife.bind(this);
        includeTopTvTital.setText("详细信息");
        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setNestedScrollingEnabled(false);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(HistoryDetailsActivity.this));
        initHttpData();
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_history_details;
    }
    private void initHttpData() {
        Intent intent = getIntent();
        if (intent!=null)
        {
            String taskId = intent.getStringExtra("taskId");
            String id = (String) SPUtils.get(HistoryDetailsActivity.this, AppAllKey.User_ID, "");
            NetWorkUtlis netWorkUtlis = new NetWorkUtlis();
            netWorkUtlis.setOnNetWork(URL_GET.getHistoryTaskData(id,taskId), this);
        }
    }
    @Override
    public void onHandleMessage(Message msg) {
        super.onHandleMessage(msg);
        switch (msg.what) {
            case CommonParameter.HANDLE_MSG_SUCCESS:
                if (!StrUtils.isEmpty(msg.obj.toString())&& HelpUtils.IsSucessRecord(msg.obj.toString())) {
//                    try {
                    DataHistoryDetails dataHistoryDetails = JSON.parseObject(msg.obj.toString(), DataHistoryDetails.class);
                    DataHistoryDetails.RecordBean.OrderExpressBean orderExpress = dataHistoryDetails.getRecord().getOrderExpress();
                    if (orderExpress!=null)
                    {
                        List<DataHistoryDetails.RecordBean.OrderExpressBean.ExpressDataBean> datas = orderExpress.getExpressData();
                        initAdapter(datas);
                    }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
                }
                break;
        }
    }
    HistoryDetailAdapter homeAdapter = null;
    private void initAdapter(List<DataHistoryDetails.RecordBean.OrderExpressBean.ExpressDataBean> datas) {
        homeAdapter = new HistoryDetailAdapter(datas,"6");
        mRecyclerview.setAdapter(homeAdapter);
        homeAdapter.notifyDataSetChanged();
    }
}
