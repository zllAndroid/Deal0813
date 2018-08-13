package com.xm6leefun.zlldeal.main_code.mains;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentTabHost;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabWidget;
import android.widget.TextView;

import com.xm6leefun.zlldeal.R;
import com.xm6leefun.zlldeal.base.AppManager;
import com.xm6leefun.zlldeal.base.MyBaseActivity;
import com.xm6leefun.zlldeal.utils.ToastUtil;
import com.xm6leefun.zlldeal.utils.VersionCheckUtils;
import com.xm6leefun.zlldeal.utils.VersionCheckUtilsPHP;

import butterknife.BindView;

public class MainActivity extends MyBaseActivity {
//    int[] imgs = {R.drawable.tab_home, R.drawable.tab_mine};
    int[] imgs = {R.drawable.tab_home, R.drawable.tab_tui,R.drawable.tab_order, R.drawable.tab_mine};
    private String[] tvtab ={ "行情","买卖","企业", "我的" };
//    private String[] tvtab ={ "当前任务", "个人中心", "交易查询","我的" };

    @BindView(android.R.id.tabs)
    TabWidget tabs;

    @BindView(android.R.id.tabhost)
    FragmentTabHost mTabHost;
    public static int screenWidth;
    public static int screenHeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        //		获取屏幕分辨率
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;
//        ButterKnife.bind(this);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
//        去掉分割线
        mTabHost.getTabWidget().setDividerDrawable(android.R.color.transparent);
        mTabHost.addTab(mTabHost.newTabSpec("home").setIndicator(getIndecator(0)), HomeFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("maimai").setIndicator(getIndecator(1)), MaimaiFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("company").setIndicator(getIndecator(2)), CompanyFragment.class, null);
//        mTabHost.addTab(mTabHost.newTabSpec("home").setIndicator(getIndecator(0)), OrderFragment.class, null);
//        mTabHost.addTab(mTabHost.newTabSpec("popu").setIndicator(getIndecator(1)), PopularizeFragment.class, null);
//        mTabHost.addTab(mTabHost.newTabSpec("order").setIndicator(getIndecator(2)), OrderFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("mine").setIndicator(getIndecator(3)), MineFragment.class, null);
//        int localVersion = HelpUtils.getLocalVersion(MainActivity.this);
        VersionCheckUtilsPHP.initUpdata(false);
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    private View getIndecator(int index) {
        View view = getLayoutInflater().inflate(R.layout.layout_tabin, null);
        ImageView mImageView = (ImageView)view.findViewById(R.id.img_main_tab);
        final TextView mTextView =  (TextView)view.findViewById(R.id.tv_main_tab);

        try {
            mTextView.setText(tvtab[index]);
            mImageView.setImageResource(imgs[index]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }
    boolean isExit;
    Handler mHandler = new Handler();
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            // 双击退出
            if (isExit)
            {
                AppManager.getAppManager().onAppExit(MainActivity.this);
//                finish();
            } else
            {
                isExit = true;
                ToastUtil.show("再按一次退出应用");
//                Toast.makeText(this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
                mHandler.postDelayed(new Runnable()
                {
                    public void run()
                    {
                        isExit = false;
                    }
                }, 2000);
            }
            // return super.onKeyDown(keyCode, event);
            // 拦截系统按键
        }
        return true;
    }
//    拦截右滑退出
    @Override
    protected boolean isSupportSwipeBack() {
        return false;
    }
    @Override
    protected boolean isTopBack() {
        return false;
    }
}

