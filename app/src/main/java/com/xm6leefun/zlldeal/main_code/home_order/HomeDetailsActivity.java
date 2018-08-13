package com.xm6leefun.zlldeal.main_code.home_order;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xm6leefun.model.DataHomeDetails;
import com.xm6leefun.model.DataKuaidi;
import com.xm6leefun.model.RealmTaskResule;
import com.xm6leefun.zlldeal.R;
import com.xm6leefun.zlldeal.adapter.RealmAdapter;
import com.xm6leefun.zlldeal.base.AppAllKey;
import com.xm6leefun.zlldeal.base.CommonParameter;
import com.xm6leefun.zlldeal.base.MyBaseActivity;
import com.xm6leefun.zlldeal.base.URL_GET;
import com.xm6leefun.zlldeal.main_code.about_realm.DefaultItemTouchHelpCallback;
import com.xm6leefun.zlldeal.main_code.about_realm.RealmHelper;
import com.xm6leefun.zlldeal.main_code.home_order.popwindows.ChooseKuaidiWindow;
import com.xm6leefun.zlldeal.ui_custom.CusScrollView;
import com.xm6leefun.zlldeal.utils.DialogUtils;
import com.xm6leefun.zlldeal.utils.IntentUtils;
import com.xm6leefun.zlldeal.utils.NetWorkUtlis;
import com.xm6leefun.zlldeal.utils.NoDoubleClickUtils;
import com.xm6leefun.zlldeal.utils.SPUtils;
import com.xm6leefun.zlldeal.utils.StrUtils;
import com.xm6leefun.zlldeal.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

//商户入驻
public class HomeDetailsActivity extends MyBaseActivity implements ChooseKuaidiWindow.OnClickChooseWho ,CusScrollView.OnScrollListener {

    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    @BindView(R.id.hd_tv_scan_result)
    TextView hdTvScanResult;
    @BindView(R.id.his_details_tv_order_id)
    TextView hisDetailsTvOrderId;
    @BindView(R.id.his_details_tv_order_name)
    TextView hisDetailsTvOrderName;
    @BindView(R.id.his_details_tv_order_num)
    TextView hisDetailsTvOrderNum;
    @BindView(R.id.his_details_tv_shou_name)
    TextView hisDetailsTvShouName;
    @BindView(R.id.his_details_tv_shou_phone)
    TextView hisDetailsTvShouPhone;
    @BindView(R.id.his_details_tv_shou_addr)
    TextView hisDetailsTvShouAddr;
    @BindView(R.id.hd_tv_kuaidi)
    TextView hdTvKuaidi;
    @BindView(R.id.hd_ed_logiticsdanhao)
    EditText hdEdLogiticsdanhao;
    @BindView(R.id.hd_main)
    RelativeLayout mMain;
    @BindView(R.id.hd_tv_lin_kuaidi)
    LinearLayout hdTvLinKuaidi;
    @BindView(R.id.sd_lin_top)
    LinearLayout sdvLinTop;
    @BindView(R.id.sd_lin_xian)
    LinearLayout sdvLinXian;
    @BindView(R.id.sd_lin_yin)
    LinearLayout sdvLinYin;
    @BindView(R.id.sd_myscroll)
    CusScrollView sdScrollView;

    @BindView(R.id.hd_tv_recyclerview)
    RecyclerView mRecyclerview;

    private List<RealmTaskResule> mRealmList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_ship_details);
//        ButterKnife.bind(this);
        sdScrollView.setOnScrollListener(this);
//        初始化在顶部
        sdScrollView.smoothScrollTo(0,20);
        includeTopTvTital.setText("发货");
        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setNestedScrollingEnabled(false);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(HomeDetailsActivity.this));
        initHttpData();

//        initAdapter();
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_ship_details;
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }
    private RealmHelper mRealmHelper;
    RealmAdapter  mAdapter;
    //    private List<RealmTaskResule> mDogs = new ArrayList<>();
    private void initData() {
        mRealmHelper = new RealmHelper(this);

        mRealmList = mRealmHelper.queryResultBytaskId(taskId);
//        mRealmList = mRealmHelper.queryAllDog();
        mAdapter = new RealmAdapter(mRealmList);
        mRecyclerview.setAdapter(mAdapter);
        hdTvScanResult.setText((mRealmHelper.queryResultBytaskId(taskId).size())+"个");
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view,final int position) {
                if (view.getId() ==R.id.item_realm_tv_play)
                {
                    DialogUtils.showDialog("确定删除第"+(position+1)+"条扫描的结果吗？", new DialogUtils.OnClickSureListener() {
                        @Override
                        public void onClickSure() {
                            //删除数据库数据
                            mRealmHelper.deleteDog(mRealmList.get(position).getQrcode());
                            //滑动删除
//                         mRealmList.remove(position);
                            mAdapter.notifyItemRemoved(position);
                            hdTvScanResult.setText((mRealmHelper.queryResultBytaskId(taskId).size())+"个");
                        }
                    });
//                    Tip.getDialog(HomeDetailsActivity.this, "确定删除第"+(position+1)+"条扫描的结果吗？", new Tip.OnClickSureListener() {
//                        @Override
//                        public void onClickSure() {
//                            //删除数据库数据
//                            mRealmHelper.deleteDog(mRealmList.get(position).getQrcode());
//                            //滑动删除
////                         mRealmList.remove(position);
//                            mAdapter.notifyItemRemoved(position);
//                            hdTvScanResult.setText((mRealmHelper.queryResultBytaskId(taskId).size())+"个");
//                        }
//                    });
                }
            }
        });
//        添加滑动删除
//        setSwipeDelete();
        mAdapter.notifyDataSetChanged();
    }
    private void setSwipeDelete() {
        DefaultItemTouchHelpCallback mCallback = new DefaultItemTouchHelpCallback(new DefaultItemTouchHelpCallback.OnItemTouchCallbackListener() {
            @Override
            public void onSwiped(int adapterPosition) {
                //删除数据库数据
                mRealmHelper.deleteDog(mRealmList.get(adapterPosition).getId());
                //滑动删除
                mRealmList.remove(adapterPosition);
                mAdapter.notifyItemRemoved(adapterPosition);
            }
            @Override
            public boolean onMove(int srcPosition, int targetPosition) {

                return false;
            }
        });
        mCallback.setDragEnable(false);
        mCallback.setSwipeEnable(true);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(mCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerview);
    }
    public  static  String taskId =null;
    private void initHttpData() {
        String id = (String) SPUtils.get(this, AppAllKey.User_ID, "");
        Intent intent = getIntent();
        taskId = intent.getStringExtra("taskId");
        if (taskId != null) {
            NetWorkUtlis netWorkUtlis = new NetWorkUtlis();
            netWorkUtlis.setOnNetWork(AppAllKey.LodingFlower, URL_GET.getWaitShipTaskData(id, taskId), this);
        }
    }

    @Override
    public void onHandleMessage(Message msg) {
        super.onHandleMessage(msg);
        switch (msg.what) {
            case CommonParameter.HANDLE_MSG_SUCCESS:
//                boolean b = HelpUtils.IsSucessRecord(msg.obj.toString());
                if (!StrUtils.isEmpty(msg.obj.toString())) {
                    DataHomeDetails dataHomeDetails = JSON.parseObject(msg.obj.toString(), DataHomeDetails.class);
                    DataHomeDetails.RecordBean.TaskRecordBean taskData = dataHomeDetails.getRecord().getTaskData();
                    initUI(taskData);
                }
                break;
        }
    }

    private void initUI(DataHomeDetails.RecordBean.TaskRecordBean taskData) {
        hisDetailsTvOrderId.setText(taskData.getOrderNum());
        hisDetailsTvOrderName.setText(taskData.getGoodsName());
        hisDetailsTvOrderNum.setText(taskData.getBuyNum());
        hisDetailsTvShouName.setText(taskData.getName());
        hisDetailsTvShouPhone.setText(taskData.getPhone());
        hisDetailsTvShouAddr.setText(taskData.getRegion());
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (data != null) {
                String code = data.getStringExtra("code");
                if (!StrUtils.isEmpty(code) && code != null) {
                    hdTvScanResult.setText(code);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    String string = "";
    int code = 0;
    ChooseKuaidiWindow chooseKuaidiWindow = null;
    @OnClick({R.id.hd_tv_lin_kuaidi, R.id.hd_btn_logiticsdanhao, R.id.ps_btn_tijiao, R.id.hd_lin_scan,R.id.hd_tv_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.hd_tv_lin_kuaidi:
                int width = hdTvLinKuaidi.getMeasuredWidth();
                int height = hdTvLinKuaidi.getMeasuredHeight();
                if (NoDoubleClickUtils.isDoubleClick()) {
                    if (chooseKuaidiWindow == null)
                        chooseKuaidiWindow = new ChooseKuaidiWindow(HomeDetailsActivity.this, height + 23, width + 10);
                    chooseKuaidiWindow.showAtLocation(mMain, Gravity.BOTTOM, 0, 0);
                    chooseKuaidiWindow.setOnClickChooseWho(this);
                }
                break;
            case R.id.hd_btn_logiticsdanhao:
                code++;
                string =code+"z";
                Log.e("string",string+"");
                RealmTaskResule realmTaskResules = new RealmTaskResule();
                realmTaskResules.setExpressNum("123"+string);
                realmTaskResules.setQrcode("000"+string);
                realmTaskResules.setId(string+"");
                realmTaskResules.setTaskId(taskId+"");
//                mRealmList.add(realmTaskResule);
                if (!mRealmHelper.isDogExist(string+"")) {
//                    mRealmList.add(realmTaskResule);
                    mRealmHelper.addDog(realmTaskResules);
                    mAdapter.notifyDataSetChanged();
                    hdTvScanResult.setText((mRealmHelper.queryResultBytaskId(taskId).size())+"个");
                } else {
                    ToastUtil.show("已经存在");
                }
//                realmAdd();
                break;
            case R.id.hd_tv_clear:
                if ((mRealmList.size())==0)
                {
                    ToastUtil.show("扫描结果已经为空");
                    return;
                }
                DialogUtils.showDialog("确定全部删除扫描的结果吗？", new DialogUtils.OnClickSureListener() {
                    @Override
                    public void onClickSure() {
                        mRealmHelper.deleteDogBytaskId(taskId);
//                        mRealmList.clear();
                        mAdapter.notifyDataSetChanged();
                        hdTvScanResult.setText((mRealmHelper.queryResultBytaskId(taskId).size())+"个");
                    }
                });
//                Tip.getDialog(HomeDetailsActivity.this, "确定全部删除扫描的结果吗？", new Tip.OnClickSureListener() {
//                    @Override
//                    public void onClickSure() {
//                        mRealmHelper.deleteDogBytaskId(taskId);
////                        mRealmList.clear();
//                        mAdapter.notifyDataSetChanged();
//                        hdTvScanResult.setText((mRealmHelper.queryResultBytaskId(taskId).size())+"个");
//                    }
//                });
                break;
            case R.id.ps_btn_tijiao:
                code++;
                string =code+"s";
                Log.e("string",string+"");
                RealmTaskResule realmTaskResule = new RealmTaskResule();
                realmTaskResule.setNfcUid("123"+string);
                realmTaskResule.setQrcode("000"+string);
                realmTaskResule.setId(string+"");
                realmTaskResule.setTaskId(taskId+"");
//                mRealmList.add(realmTaskResule);
                if (!mRealmHelper.isDogExist(string+"")) {
//                    mRealmList.add(realmTaskResule);
                    mRealmHelper.addDog(realmTaskResule);
                    mAdapter.notifyDataSetChanged();
                    hdTvScanResult.setText((mRealmHelper.queryResultBytaskId(taskId).size())+"个");
                } else {
                    ToastUtil.show("已经存在");
                }

//                RealmResults<ModelScanRe
//
// sule> userList = mRealm.where(ModelScanResule.class)
//                        .findAllAsync();
//                if (userList.isLoaded())
//                {
//
//                    Log.e("userList","查询后"+userList.toString());
//                }
                break;
            case R.id.hd_lin_scan:
//                code++;
//                string =code+"z";
//                Log.e("string",string+"");
//                RealmTaskResule realmTaskResule = new RealmTaskResule();
//                realmTaskResule.setExpressNum("123"+string);
//                realmTaskResule.setQrcode("000"+string);
//                realmTaskResule.setId(string+"");
//
//                if (!mRealmHelper.isDogExist(string+"")) {
//                    mRealmList.add(realmTaskResule);
//                    mRealmHelper.addDog(realmTaskResule);
//                    mAdapter.notifyDataSetChanged();
//                } else {
//                    ToastUtil.show("已经存在");
//                }


//                RealmQuery<ModelScanResule> query = mRealm.where(ModelScanResule.class);
//                RealmResults<ModelScanResule> userList1 = query.findAll();
////                ModelScanResule user2 = mRealm.where(ModelScanResule.class).findAll();
//                if (userList1.isLoaded())
//                Log.e("userList","user2查询后"+userList1.get(0).getTaskList().get(0).getExpressId().toString());

                if (NoDoubleClickUtils.isDoubleClick()) {
                    IntentUtils.JumpTo(ScanCodeActivity.class);
//                    Intent intent = new Intent();
//                    intent.setClass(HomeDetailsActivity.this, ScanCodeActivity.class);
//                    startActivityForResult(intent, 0);
                }
                break;
        }
    }

    String  expressId = null;
    @Override
    public void ClickChooseWho(DataKuaidi.RecordBean.ExpressBean item) {
        try {
            hdTvKuaidi.setText(item.getName());
            this.expressId=item.getId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        chooseKuaidiWindow.dismiss();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onScroll(int scrollY) {
        int height = sdvLinTop.getHeight();
        if (scrollY>height)
        {
            sdvLinYin.setVisibility(View.VISIBLE);
            sdvLinXian.setVisibility(View.INVISIBLE);
        }else
        {
            sdvLinYin.setVisibility(View.INVISIBLE);
            sdvLinXian.setVisibility(View.VISIBLE);
        }


    }
}
