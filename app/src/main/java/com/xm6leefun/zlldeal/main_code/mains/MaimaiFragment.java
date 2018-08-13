package com.xm6leefun.zlldeal.main_code.mains;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xm6leefun.zlldeal.R;
import com.xm6leefun.zlldeal.base.BaseFragment;
import com.xm6leefun.zlldeal.main_code.child_deals.FiveDealFragment;
import com.xm6leefun.zlldeal.main_code.child_deals.FourDealFragment;
import com.xm6leefun.zlldeal.main_code.child_deals.OneDealFragment;
import com.xm6leefun.zlldeal.main_code.child_deals.ThrDealFragment;
import com.xm6leefun.zlldeal.main_code.child_deals.TwoDealFragment;
import com.xm6leefun.zlldeal.ui_custom.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MaimaiFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.main_view_top)
    View mainViewTop;
    @BindView(R.id.main_view_title)
    TextView mainViewTitle;
    @BindView(R.id.mai_btn_one)
    TextView maiBtnOne;
    @BindView(R.id.mai_btn_two)
    TextView maiBtnTwo;
    @BindView(R.id.mai_btn_thr)
    TextView maiBtnThr;
    @BindView(R.id.mai_btn_fou)
    TextView maiBtnFou;
    @BindView(R.id.mai_btn_fiv)
    TextView maiBtnFiv;
    @BindView(R.id.mai_viewpager)
    CustomViewPager maiViewpager;

    public MaimaiFragment() {
    }

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_maimai, container, false);
        }
        unbinder = ButterKnife.bind(this, view);
        mainViewTitle.setText("买卖");

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            if (mainViewTop != null)
//                mainViewTop.setVisibility(View.VISIBLE);
//        }
        initUI();
        return view;
    }
    private UpFragment adapter =null;
    private void initUI() {
//        禁止滑动
        maiViewpager.setScanScroll(false);
        if (adapter==null) {
            adapter = new UpFragment(getChildFragmentManager(), getFragment());
//            adapter = new UpFragment(getSupportFragmentManager(), getFragment());
            maiViewpager.setAdapter(adapter);
        }
        initCurrent(0,maiBtnOne);
//        maiViewpager.setCurrentItem(0);
//        maiBtnOne.setTextColor(ContextCompat.getColor(getActivity(), R.color.app_theme));
//        maiBtnTwo.setTextColor(ContextCompat.getColor(getActivity(), R.color.gray666));
    }
    private void initCurrent(int position,TextView mBtn) {
        maiViewpager.setCurrentItem(position,false);
        maiBtnOne.setTextColor(ContextCompat.getColor(getActivity(), R.color.gray666));
        maiBtnTwo.setTextColor(ContextCompat.getColor(getActivity(), R.color.gray666));
        maiBtnThr.setTextColor(ContextCompat.getColor(getActivity(), R.color.gray666));
        maiBtnFou.setTextColor(ContextCompat.getColor(getActivity(), R.color.gray666));
        maiBtnFiv.setTextColor(ContextCompat.getColor(getActivity(), R.color.gray666));

        maiBtnOne.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
        maiBtnTwo.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
        maiBtnThr.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
        maiBtnFou.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
        maiBtnFiv.setBackgroundColor(getActivity().getResources().getColor(R.color.white));

        mBtn.setBackgroundColor(getActivity().getResources().getColor(R.color.app_theme));
        mBtn.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
    }
    public List<Fragment> getFragment() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new OneDealFragment());
        fragments.add(new TwoDealFragment());
        fragments.add(new ThrDealFragment());
        fragments.add(new FourDealFragment());
        fragments.add(new FiveDealFragment());
        return fragments;
    }
    class UpFragment extends FragmentPagerAdapter {
        private List<Fragment> fragments;

        public UpFragment(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    @OnClick({R.id.mai_btn_one, R.id.mai_btn_two, R.id.mai_btn_thr, R.id.mai_btn_fou, R.id.mai_btn_fiv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mai_btn_one:
                initCurrent(0,maiBtnOne);
                break;
            case R.id.mai_btn_two:
                initCurrent(1,maiBtnTwo);
                break;
            case R.id.mai_btn_thr:
                initCurrent(2,maiBtnThr);
                break;
            case R.id.mai_btn_fou:
                initCurrent(3,maiBtnFou);
                break;
            case R.id.mai_btn_fiv:
                initCurrent(4,maiBtnFiv);
                break;
        }
    }
}
