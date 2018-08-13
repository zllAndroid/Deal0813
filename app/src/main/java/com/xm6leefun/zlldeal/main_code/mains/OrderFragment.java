package com.xm6leefun.zlldeal.main_code.mains;

import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xm6leefun.model.DataHome;
import com.xm6leefun.zlldeal.R;
import com.xm6leefun.zlldeal.adapter.HomeAdapter;
import com.xm6leefun.zlldeal.base.AppAllKey;
import com.xm6leefun.zlldeal.base.BaseFragment;
import com.xm6leefun.zlldeal.base.CommonParameter;
import com.xm6leefun.zlldeal.base.URL_GET;
import com.xm6leefun.zlldeal.main_code.home_order.HistoryOrderMainActivity;
import com.xm6leefun.zlldeal.main_code.home_order.HomeDetailsActivity;
import com.xm6leefun.zlldeal.utils.IntentUtils;
import com.xm6leefun.zlldeal.utils.NetWorkUtlis;
import com.xm6leefun.zlldeal.utils.NoDoubleClickUtils;
import com.xm6leefun.zlldeal.utils.SPUtils;
import com.xm6leefun.zlldeal.utils.StrUtils;
import com.xm6leefun.zlldeal.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.main_view_top)
    View mainViewTop;
    @BindView(R.id.main_view_title)
    TextView mainViewTitle;
    @BindView(R.id.main_view_right)
    TextView mainViewRight;

    @BindView(R.id.fragment_home_recyclerview)
    RecyclerView mRecyclerview;

    public OrderFragment() {
    }

    View view;
    int page = 1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_now, container, false);
        }
        unbinder = ButterKnife.bind(this, view);
        mainViewTitle.setText("首页");
        mainViewRight.setVisibility(View.VISIBLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (mainViewTop != null)
                mainViewTop.setVisibility(View.VISIBLE);
        }

        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setNestedScrollingEnabled(false);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        initHttpData();
        UpLoding();
        return view;
    }

    private void initHttpData() {
        String id = (String) SPUtils.get(getActivity(), AppAllKey.User_ID, "");
        NetWorkUtlis netWorkUtlis = new NetWorkUtlis();
        netWorkUtlis.setOnNetWork(AppAllKey.LodingFlower, URL_GET.getWaitShipTask(id,page+""), this);
    }
    List<DataHome.RecordBean.TaskListBean> mList = new ArrayList<>();
    boolean isHave = true;
    DataHome.RecordBean data =null;
    @Override
    protected void onHandleMessage(Message msg) {
        super.onHandleMessage(msg);
        switch (msg.what) {
            case CommonParameter.HANDLE_MSG_SUCCESS:
                if (!StrUtils.isEmpty(msg.obj.toString())) {
                    try {
                        DataHome dataNews = JSON.parseObject(msg.obj.toString(), DataHome.class);
                        data = dataNews.getRecord();
                        if (data!=null) {
                            List<DataHome.RecordBean.TaskListBean> taskList = data.getTaskList();
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
//                                initAdapter();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }
    HomeAdapter homeAdapter = null;
//    private void initAdapter() {
//        try {
//            homeAdapter = new HomeAdapter(mList);
//            mRecyclerview.setAdapter(homeAdapter);
//            homeAdapter.notifyDataSetChanged();
//            homeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//                @Override
//                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                    DataHome.RecordBean.TaskListBean item = (DataHome.RecordBean.TaskListBean)adapter.getItem(position);
//                    if (item!=null)
//                    IntentUtils.JumpToHaveOne(HomeDetailsActivity.class,"taskId",item.getTaskId());
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    @OnClick(R.id.main_view_right)
    public void onViewClicked() {
        if (NoDoubleClickUtils.isDoubleClick())
            IntentUtils.JumpTo(HistoryOrderMainActivity.class);
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
    @Override
    public void onDestroy() {
        super.onDestroy();
        view = null;
    }
}
