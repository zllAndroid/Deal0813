package com.xm6leefun.zlldeal.main_code.child_deals;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xm6leefun.zlldeal.R;
import com.xm6leefun.zlldeal.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class FiveDealFragment extends BaseFragment {
    Unbinder unbinder;
//    @BindView(R.id.total_recyclerview)
//    RecyclerView mRecyclerview;
//    @BindView(R.id.lin_main)
//    LinearLayout mLinMain;

    public FiveDealFragment() {
    }

    //    第几
    String type = "1";
//    List<DataFanYongDetailsOne.RecordBean.ListBean> mList = new ArrayList<>();
    View view;
    int page = 1;
    boolean isHave = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_deal_five, container, false);
        }
        unbinder = ButterKnife.bind(this, view);
//        mRecyclerview.setHasFixedSize(true);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
//        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
//        mRecyclerview.setLayoutManager(linearLayoutManager);
//        mRecyclerview.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
//        isHave = true;
//        initHttp();
//        UpLoding();
        return view;
    }

//    private void initHttp() {
//        NetWorkUtlis netWorkUtlis = new NetWorkUtlis();
//        netWorkUtlis.setOnNetWork(AppAllKey.LodingFlower, URL_GET.commissionOutDetail(type, page + ""), this);
//    }

//    @Override
//    protected void onFragmentHandleMessage(Message msg) {
//        super.onFragmentHandleMessage(msg);
//        switch (msg.what) {
//            case CommonParameter.HANDLE_MSG_SUCCESS:
//                if (!StrUtils.isEmpty(msg.obj.toString())) {
//                    Log.e("isHave", "返回值等于" + msg.obj.toString());
//                    try {
//                        DataFanYongDetailsOne dataFanYongDetailsTwo = JSON.parseObject(msg.obj.toString(), DataFanYongDetailsOne.class);
//                        record = dataFanYongDetailsTwo.getRecord();
//                        List<DataFanYongDetailsOne.RecordBean.ListBean> list = record.getList();
//                        if (list != null) {
//                            if (isHave) {
//                                mList.clear();
//                                isHave = false;
//                            }
//                            mList.addAll(list);
//                            initAdapter();
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//                break;
//        }
//    }

//    public void UpLoding() {
//        mRecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
//                // 当不滑动时
//                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                    // 获取最后一个完全显示的itemPosition
//                    int lastItemPosition = manager.findLastCompletelyVisibleItemPosition();
//                    int itemCount = manager.getItemCount();
//                    // 判断是否滑动到了最后一个Item，并且是向左滑动
//                    if (lastItemPosition == (itemCount - 1)) {
//                        // 加载更多
//                        if (record.getPageinfo() != null) {
//                            if (record.getPageinfo().getPage() < record.getPageinfo().getPages()) {
//                                page = record.getPageinfo().getPage();
//                                page++;
//                                initHttp();
//                            } else {
//                                if (mList.size() != 0)
//                                    ToastUtil.show("只有这么多了");
//                            }
//                        }
//                    }
//                }
//            }
//        });
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        view = null;
        unbinder.unbind();
    }
}
