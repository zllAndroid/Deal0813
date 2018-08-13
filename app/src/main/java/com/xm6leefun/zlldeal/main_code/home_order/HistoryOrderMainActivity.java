package com.xm6leefun.zlldeal.main_code.home_order;

import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xm6leefun.model.DataHistory;
import com.xm6leefun.zlldeal.R;
import com.xm6leefun.zlldeal.adapter.HistoryMainAdapter;
import com.xm6leefun.zlldeal.base.AppAllKey;
import com.xm6leefun.zlldeal.base.CommonParameter;
import com.xm6leefun.zlldeal.base.MyBaseActivity;
import com.xm6leefun.zlldeal.base.URL_GET;
import com.xm6leefun.zlldeal.utils.IntentUtils;
import com.xm6leefun.zlldeal.utils.NetWorkUtlis;
import com.xm6leefun.zlldeal.utils.SPUtils;
import com.xm6leefun.zlldeal.utils.StrUtils;
import com.xm6leefun.zlldeal.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

//商户入驻
public class HistoryOrderMainActivity extends MyBaseActivity {

    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    //    @BindView(R.id.include_top_lin_back)
//    LinearLayout includeTopLinBack;
    @BindView(R.id.history_recyc)
    RecyclerView mRecyclerview;
    boolean isHave = true;
    DataHistory.RecordBean data =null;
    int page = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_history_main);
//        ButterKnife.bind(this);
        includeTopTvTital.setText("历史订单列表");
        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setNestedScrollingEnabled(false);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(HistoryOrderMainActivity.this));
        initHttpData();
        UpLoding();
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_history_main;
    }
    private void initHttpData() {
        String id = (String) SPUtils.get(HistoryOrderMainActivity.this, AppAllKey.User_ID, "");
        NetWorkUtlis netWorkUtlis = new NetWorkUtlis();
        netWorkUtlis.setOnNetWork(URL_GET.getHistoryTask(id,page+""), this);
    }

    @Override
    public void onHandleMessage(Message msg) {
        super.onHandleMessage(msg);
        switch (msg.what) {
            case CommonParameter.HANDLE_MSG_SUCCESS:
                if (!StrUtils.isEmpty(msg.obj.toString())) {
                    try {
                        DataHistory dataHistoryMain = JSON.parseObject(msg.obj.toString(), DataHistory.class);
                        data = dataHistoryMain.getRecord();
                        if (data!=null) {
                            List<DataHistory.RecordBean.TaskListBean> taskList = data.getTaskList();
                            if (taskList != null) {
                                if (isHave) {
                                    mList.clear();
                                    isHave = false;
                                }
                                if (page==1)
                                {
                                    mList.clear();
                                }
                                mList.addAll(taskList);
                                initAdapter();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    ArrayList<DataHistory.RecordBean.TaskListBean> mList = new ArrayList<>();
    HistoryMainAdapter historyAdapter = null;
    private void initAdapter() {

//        for (int i =0;i<=30;i++)
//        {
//            mList.add("");
//        }
        historyAdapter = new HistoryMainAdapter(mList);
        mRecyclerview.setAdapter(historyAdapter);
        historyAdapter.notifyDataSetChanged();
        historyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                IntentUtils.JumpTo(HistoryDetailsActivity.class);
                DataHistory.RecordBean.TaskListBean item = (DataHistory.RecordBean.TaskListBean)adapter.getItem(position);
                IntentUtils.JumpToHaveOne(HistoryOrderActivity.class,"time",item.getCreateTimeStr());
            }
        });
    }

    public void UpLoding() {
        mRecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                // 当不滑动时
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    // 获取最后一个完全显示的itemPosition
                    int lastItemPosition = manager.findLastCompletelyVisibleItemPosition();
                    int itemCount = manager.getItemCount();
                    // 判断是否滑动到了最后一个Item，并且是向左滑动
                    if (lastItemPosition == (itemCount - 1)) {
                        // 加载更多
                        if ( data!=null) {
                            if ( data.getPageNo() < data.getTotal()) {
                                page =  data.getPageNo();
                                page++;
                                initHttpData();
                            }else {
                                if (mList.size()!=0)
                                    ToastUtil.show("只有这么多了");
                            }
                        }
                    }
                }
            }
        });
    }
}
