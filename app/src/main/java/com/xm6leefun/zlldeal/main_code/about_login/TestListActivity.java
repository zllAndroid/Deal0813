//package com.xm6leefun.zlldeal.main_code.about_login;
//
//import android.os.Bundle;
//import android.support.v7.widget.GridLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.View;
//import android.widget.ImageView;
//
//import com.chad.library.adapter.base.BaseQuickAdapter;
//import com.xm6leefun.zlldeal.R;
//import com.xm6leefun.zlldeal.adapter.HuaAdapter;
//import com.xm6leefun.zlldeal.base.MyBaseActivity;
//
//import java.util.ArrayList;
//import java.util.List;
//
////@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
//public class TestListActivity extends MyBaseActivity {
//    RecyclerView mRecyclerview;
//    private OnDragButton dragButton;
//    private ImageView mIv_View,mIv_View1,mIv_View2,mIv_View3,mIv_View4,mIv_View5,mIv_View6;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        initTest();
//    }
//    @Override
//    public int getLayoutId() {
//        return R.layout.activity_main;
////        return R.layout.activity_test;
//    }
//    //    拦截右滑退出
//    @Override
//    protected boolean isSupportSwipeBack() {
//        return false;
//    }
//
//    @Override
//    protected boolean isTopBack() {
//        return false;
//    }
//
//    @Override
//    protected void initBaseUI() {
//        super.initBaseUI();
//
////        initAdapter();
//
//    }
//
//    private void initTest() {
//        dragButton = (OnDragButton)findViewById(R.id.tv_drag);
//        mIv_View =(ImageView)findViewById(R.id.iv_image);
//        mIv_View1 =(ImageView)findViewById(R.id.iv_image1);
//        mIv_View2 =(ImageView)findViewById(R.id.iv_image2);
//        mIv_View3 =(ImageView)findViewById(R.id.iv_image3);
//        mIv_View4 =(ImageView)findViewById(R.id.iv_image4);
//        mIv_View5=(ImageView)findViewById(R.id.iv_image5);
//        mIv_View6=(ImageView)findViewById(R.id.iv_image6);
//        ParabolaAnimation.ParabolaValueAnimation(mIv_View4);
//        ParabolaAnimation.HorizontalAnimation(mIv_View1);
//        ParabolaAnimation.starthalfAnimation(mIv_View2);
//        ParabolaAnimation.propertyValuesHolder(mIv_View3);
//        ParabolaAnimation.parabolaAnimation(mIv_View);
//        ParabolaAnimation.propertyValuesHolder(mIv_View5);
//        ParabolaAnimation.startRotateSmallAnimation(mIv_View6);
//    }
//
//    List<String> mList = new ArrayList<>();
//    HuaAdapter homeAdapter = null;
//    private void initAdapter() {
//        mRecyclerview =(RecyclerView) findViewById(R.id.per_already_recyc);
//        mRecyclerview.setHasFixedSize(true);
//        mRecyclerview.setNestedScrollingEnabled(false);
//        mRecyclerview.setLayoutManager(new GridLayoutManager(this,3));
//        for (int i=0;i<30;i++)
//        {
//            mList.add("1");
//        }
////        加线
////        mRecyclerview.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
//        homeAdapter = new HuaAdapter(mList);
//        mRecyclerview.setAdapter(homeAdapter);
//        homeAdapter.notifyDataSetChanged();
//        homeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
////				DataAlreadyJiHuo.RecordBean.ActivateListBean item = (DataAlreadyJiHuo.RecordBean.ActivateListBean)adapter.getItem(position);
//////                ToastUtil.show(item.getWwiconTypeId());
////				String urltest = "http://trace.xm6leefun.com/bBrowser/index.html#/blockDetail/";
////				IntentUtils.JumpGoH5(getResources().getString(R.string.goods_list),urltest+item.getWwiconTypeId()+"?type=android");
//            }
//        });
//    }
//
//}
