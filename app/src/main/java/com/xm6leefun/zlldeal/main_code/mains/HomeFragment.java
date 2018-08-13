package com.xm6leefun.zlldeal.main_code.mains;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.xm6leefun.model.DataHomeTop;
import com.xm6leefun.zlldeal.R;
import com.xm6leefun.zlldeal.base.BaseFragment;
import com.xm6leefun.zlldeal.base.CommonParameter;
import com.xm6leefun.zlldeal.base.URL_GET;
import com.xm6leefun.zlldeal.main_code.one_page.SubHomeFragment;
import com.xm6leefun.zlldeal.ui_custom.CustomViewPager;
import com.xm6leefun.zlldeal.utils.NetWorkUtlis;
import com.xm6leefun.zlldeal.utils.StrUtils;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.main_view_top)
    View mainViewTop;
    @BindView(R.id.main_view_title)
    TextView mainViewTitle;

    @BindView(R.id.home_viewpager)
    CustomViewPager mViewpager;
    public HomeFragment() {
    }
    private ViewPager mPager;

    private String[] shouye_tou = new String[]{"自选","茅台","青花","国酒","二锅头","老白干","梅菜","剑南春","骑士","恒大","拜仁","皇马"};
    boolean ismPager = true;
    private SmartTabLayout viewPagerTab;
    private ImageView mIv_add;
    private MyPagerAdapter myPagerAdapter;
    View view;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_home, container, false);
        }
        unbinder = ButterKnife.bind(this, view);
        mainViewTitle.setText("首页");
        initData();
        return view;
    }
    private void initUI()
    {
//        mPager = (ViewPager) view.findViewById(R.id.viewpager);
        myPagerAdapter = new MyPagerAdapter(getFragmentManager());
        viewPagerTab = (SmartTabLayout) view.findViewById(R.id.viewpagertab);
        if (ismPager)
        {
            ismPager =!ismPager;
            mViewpager.setAdapter(myPagerAdapter);
            viewPagerTab.setViewPager(mViewpager);
        }
//        Schedulers.computation()
        // 注意顺序，需要在ViewPager设置适配器之后再执行
    }
    List<String> mTopList = new ArrayList<>();
    List<DataHomeTop.RecordBean.CompanyListBean> companyList=null;
    private void initData() {
        NetWorkUtlis netWorkUtlis = new NetWorkUtlis();
        netWorkUtlis.setOnNetWork(CommonParameter.LodingFlower, URL_GET.companyList(), new NetWorkUtlis.OnNetWork() {
            @Override
            public void onNetSuccess(String result) {
                DataHomeTop dataHomeTop = JSON.parseObject(result, DataHomeTop.class);
                companyList = dataHomeTop.getRecord().getCompanyList();
//                List<String> mTopList = new ArrayList<>();
//                for (int i=0;i<=companyList.size();i++)
//                {
//                    mTopList.add(companyList.get(i).getCompanyName())
//                }
                if (companyList!=null)
                     initUI();
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    private final class MyPagerAdapter extends FragmentPagerAdapter
    {
        public CharSequence getPageTitle(int position)
        {
            return companyList.get(position).getCompanyName();
//            return shouye_tou[position];
        }
        private MyPagerAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public int getCount()
        {
            return companyList.size();
        }

        @Override
        public Fragment getItem(int position)
        {
            SubHomeFragment fragment = new SubHomeFragment();
            Bundle args = new Bundle();
            args.putInt("position", position);
            args.putString("text", companyList.get(position).getCompanyName());
            args.putString("id", companyList.get(position).getCompanyId());
            fragment.setArguments(args);
            return fragment;
        }
    }
}
