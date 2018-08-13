package com.xm6leefun.zlldeal.main_code.mains;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xm6leefun.zlldeal.R;
import com.xm6leefun.zlldeal.base.AppManager;
import com.xm6leefun.zlldeal.base.BaseFragment;
import com.xm6leefun.zlldeal.base.URL_GET;
import com.xm6leefun.zlldeal.main_code.about_mine.MineAdvicesActivity;
import com.xm6leefun.zlldeal.main_code.about_mine.MineSetActivity;
import com.xm6leefun.zlldeal.utils.IntentUtils;
import com.xm6leefun.zlldeal.utils.NoDoubleClickUtils;
import com.xm6leefun.zlldeal.utils.StrUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.main_view_top)
    View mainViewTop;
    @BindView(R.id.mine_iv_head)
    ImageView mineIvHead;
    @BindView(R.id.mine_tv_name)
    TextView mineTvName;
    @BindView(R.id.mine_lin_money)
    LinearLayout mineLinMoney;
    @BindView(R.id.mine_lin_customer)
    LinearLayout mineLinCustomer;
    @BindView(R.id.mine_lin_help)
    LinearLayout mineLinHelp;
    @BindView(R.id.mine_lin_set)
    LinearLayout mineLinSet;
    @BindView(R.id.mine_lin_advice)
    LinearLayout mineLinAdvice;

    public MineFragment() {
    }

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_mine, container, false);
        }
//        mainViewTitle.setText("个人中心");
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            if (mainViewTop != null)
//                mainViewTop.setVisibility(View.VISIBLE);
//        }
        unbinder = ButterKnife.bind(this, view);
//        initUIData();
        initUI();
        return view;
    }

    private void initUI() {
        if (!StrUtils.isEmpty( URL_GET.USER_HEAD))
            Glide.with(AppManager.getAppManager().currentActivity()).load( URL_GET.USER_HEAD).
                    bitmapTransform(new CropCircleTransformation(AppManager.getAppManager()
                            .currentActivity())).crossFade(1000).into(mineIvHead);
        if (!StrUtils.isEmpty(URL_GET.USER_NAME))
        {
            mineTvName.setText(URL_GET.USER_NAME);
        }



    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        view = null;
        unbinder.unbind();
    }

    @OnClick({R.id.mine_lin_money, R.id.mine_lin_customer, R.id.mine_lin_help, R.id.mine_lin_set, R.id.mine_lin_advice})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mine_lin_money:


                break;
            case R.id.mine_lin_customer:


                break;
            case R.id.mine_lin_help:


                break;
            case R.id.mine_lin_set:
                if (NoDoubleClickUtils.isDoubleClick())
                {
                    IntentUtils.JumpTo(MineSetActivity.class);
                }

                break;
            case R.id.mine_lin_advice:
                if (NoDoubleClickUtils.isDoubleClick())
                {
                    IntentUtils.JumpTo(MineAdvicesActivity.class);
                }

                break;
        }
    }
}
