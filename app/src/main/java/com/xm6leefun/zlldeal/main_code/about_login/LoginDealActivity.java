package com.xm6leefun.zlldeal.main_code.about_login;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.xm6leefun.model.DataLogin;
import com.xm6leefun.zlldeal.R;
import com.xm6leefun.zlldeal.base.AppAllKey;
import com.xm6leefun.zlldeal.base.MyBaseActivity;
import com.xm6leefun.zlldeal.base.URL_GET;
import com.xm6leefun.zlldeal.main_code.about_login.child_login.OneLoginFragment;
import com.xm6leefun.zlldeal.main_code.mains.MainActivity;
import com.xm6leefun.zlldeal.ui_custom.CustomViewPager;
import com.xm6leefun.zlldeal.utils.ACache;
import com.xm6leefun.zlldeal.utils.IntentUtils;
import com.xm6leefun.zlldeal.utils.MyLog;
import com.xm6leefun.zlldeal.utils.SPUtils;
import com.xm6leefun.zlldeal.utils.StrUtils;

import butterknife.BindView;

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
public class LoginDealActivity extends MyBaseActivity {
    public static int screenWidth;
    public static int screenHeight;
    @BindView(R.id.login_viewpager)
    CustomViewPager mViewpager;
    private ACache mCache =null;
    private String[] shouye_tou = new String[]{"账号登录","验证码登录"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initBaseUI() {
        super.initBaseUI();
        initUI();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;
        initCaChe();
    }
    private SmartTabLayout viewPagerTab;
    private MyPagerAdapter myPagerAdapter;
    boolean ismPager = true;
    private void initUI()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            findViewById(R.id.login_view_top).setVisibility(View.VISIBLE);
        }
        myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPagerTab = (SmartTabLayout) findViewById(R.id.login_viewpagertab);
        if (ismPager)
        {
            ismPager =!ismPager;
            mViewpager.setAdapter(myPagerAdapter);
            viewPagerTab.setViewPager(mViewpager);
        }
        // 注意顺序，需要在ViewPager设置适配器之后再执行
    }
    private final class MyPagerAdapter extends FragmentPagerAdapter
    {
        public CharSequence getPageTitle(int position)
        {
            return shouye_tou[position];
        }

        private MyPagerAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public int getCount()
        {
            return shouye_tou.length;
        }

        @Override
        public Fragment getItem(int position)
        {
            OneLoginFragment fragment = new OneLoginFragment();
            Bundle args = new Bundle();
            args.putInt("position", position);
            args.putString("text", shouye_tou[position]);
            fragment.setArguments(args);
            return fragment;
        }
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }
    private void initCaChe() {
        mCache = ACache.get(this);
        URL_GET.USER_TOKEN ="";
        if (mCache!=null){
            String asString = mCache.getAsString(AppAllKey.TOKEN_KEY);
            MyLog.e("asString","asString="+asString);
            if (!StrUtils.isEmpty(asString))
            {
                try {
                    DataLogin.RecordBean dataLogin = JSON.parseObject(asString, DataLogin.RecordBean.class);
                    URL_GET.USER_TOKEN = dataLogin.getToken();
                    URL_GET.USER_HEAD = dataLogin.getHeadUrl();
                    URL_GET.USER_NAME = dataLogin.getNickName();
                    URL_GET.USER_PHONE = dataLogin.getMobile();
                    SPUtils.put(LoginDealActivity.this,AppAllKey.User_NI_NAME,dataLogin.getNickName());
                    SPUtils.put(LoginDealActivity.this,AppAllKey.User_HEAD_URL,dataLogin.getHeadUrl());
                    SPUtils.put(LoginDealActivity.this,AppAllKey.User_PHONE,dataLogin.getMobile());
                    SPUtils.put(LoginDealActivity.this,AppAllKey.User_IS_SET_PSW,dataLogin.getIssetPassword());
                    ////               自动登录
                    IntentUtils.JumpFinishTo(MainActivity.class);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static final String TAG = "LoginActivity";
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
