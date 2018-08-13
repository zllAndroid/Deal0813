package com.xm6leefun.zlldeal.main_code.about_mine;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xm6leefun.zlldeal.R;
import com.xm6leefun.zlldeal.base.AppAllKey;
import com.xm6leefun.zlldeal.base.AppManager;
import com.xm6leefun.zlldeal.base.MyApplication;
import com.xm6leefun.zlldeal.base.MyBaseActivity;
import com.xm6leefun.zlldeal.main_code.about_login.LoginDealActivity;
import com.xm6leefun.zlldeal.main_code.about_mine.mine_set.ChangePhoneActivity;
import com.xm6leefun.zlldeal.utils.ACache;
import com.xm6leefun.zlldeal.utils.DataCleanManager;
import com.xm6leefun.zlldeal.utils.DialogUtils;
import com.xm6leefun.zlldeal.utils.HelpUtils;
import com.xm6leefun.zlldeal.utils.IntentUtils;
import com.xm6leefun.zlldeal.utils.NoDoubleClickUtils;
import com.xm6leefun.zlldeal.utils.Tip;
import com.xm6leefun.zlldeal.utils.ToastUtil;
import com.xm6leefun.zlldeal.utils.VersionCheckUtils;
import com.xm6leefun.zlldeal.utils.VersionCheckUtilsPHP;

import butterknife.BindView;
import butterknife.OnClick;

public class MineSetActivity extends MyBaseActivity {

    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    @BindView(R.id.include_top_lin_back)
    LinearLayout includeTopLin;
    @BindView(R.id.set_tv_cache)
    TextView setTvCache;
    @BindView(R.id.set_tv_versition)
    TextView setTvVersition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_mine_set;
    }
    @Override
    protected void initBaseUI() {
        super.initBaseUI();
        includeTopTvTital.setText("设置");
        includeTopLin.setBackgroundColor(getResources().getColor(R.color.app_theme));
        try {
            String totalCacheSize = DataCleanManager.getTotalCacheSize(MyApplication.getAppContext());
            setTvCache.setText(totalCacheSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            String versionName = HelpUtils.getLocalVersionName();
            setTvVersition.setText("v"+versionName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.set_lin_set_login_psw, R.id.set_lin_pay_set, R.id.set_lin_notify, R.id.set_lin_clear_cache, R.id.set_lin_versition, R.id.set_lin_about_me, R.id.set_btn_esc})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.set_lin_set_login_psw:
                if (NoDoubleClickUtils.isDoubleClick())
                    IntentUtils.JumpTo(ChangePhoneActivity.class);
                break;
            case R.id.set_lin_pay_set:

                break;
            case R.id.set_lin_notify:

                break;
            case R.id.set_lin_clear_cache:
                if (NoDoubleClickUtils.isDoubleClick()) {
                    if (!setTvCache.getText().toString().equals("0KB")) {
                        Tip.getDialog(MineSetActivity.this, "确认清理" + setTvCache.getText().toString() + "缓存？", new Tip.OnClickSureListener() {
                            @Override
                            public void onClickSure() {
                                cleanCaChe();
                            }
                        });
                    }else {
                        ToastUtil.show("暂无缓存");
                    }
                }
                break;
            case R.id.set_lin_versition:
                if (NoDoubleClickUtils.isDoubleClick())
                    VersionCheckUtilsPHP.initUpdata(true);
                break;
            case R.id.set_lin_about_me:

                break;
            case R.id.set_btn_esc:
                if (NoDoubleClickUtils.isDoubleClick()) {
                    DialogUtils.showDialog("确定退出本应用?", new DialogUtils.OnClickSureListener() {
                        @Override
                        public void onClickSure() {
                            Intent intent_recharge = new Intent(MineSetActivity.this, LoginDealActivity.class);
                            startActivity(intent_recharge);
                            ACache.get(MineSetActivity.this).clear();
                            AppManager.getAppManager().finishAllExceptCurrentActivity(LoginDealActivity.class);
                        }
                    });
                }
                break;
        }
    }
    //    清理缓存
    private void cleanCaChe() {
        ACache  mCache = ACache.get(this);
        String asString = mCache.getAsString(AppAllKey.TOKEN_KEY);
        Log.e("asString","清理缓存前asString="+asString);
        DataCleanManager.clearAllCache(MyApplication.getAppContext());
        try {
            String totalCacheSize = DataCleanManager.getTotalCacheSize(MyApplication.getAppContext());
            setTvCache.setText(totalCacheSize);
            ToastUtil.show("清理缓存成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        ACache  mCache2 = ACache.get(this);
        mCache2.put(AppAllKey.TOKEN_KEY, asString);
        Log.e("asString","清理缓存asString="+asString);
        String asString2 = mCache.getAsString(AppAllKey.TOKEN_KEY);
        Log.e("asString","清理缓存asString2="+asString2);
    }
}
