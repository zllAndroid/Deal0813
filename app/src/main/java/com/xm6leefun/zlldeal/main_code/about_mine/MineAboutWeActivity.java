package com.xm6leefun.zlldeal.main_code.about_mine;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xm6leefun.zlldeal.R;
import com.xm6leefun.zlldeal.base.AppManager;
import com.xm6leefun.zlldeal.base.MyBaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MineAboutWeActivity extends MyBaseActivity {

    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    @BindView(R.id.include_top_lin_back)
    LinearLayout includeTopLinBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_purse);
        ButterKnife.bind(this);
        includeTopTvTital.setText("钱包");
//        includeTopLinBack.setBackgroundColor(getResources().getColor(R.color.app_theme));
    }

    @OnClick(R.id.include_top_iv_back)
    public void onViewClicked() {
        AppManager.getAppManager().finishActivity();
    }
}
