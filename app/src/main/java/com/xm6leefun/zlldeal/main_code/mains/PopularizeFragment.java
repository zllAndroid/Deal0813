package com.xm6leefun.zlldeal.main_code.mains;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.xm6leefun.zlldeal.R;
import com.xm6leefun.zlldeal.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class PopularizeFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.main_view_top)
    View mainViewTop;
    @BindView(R.id.main_view_title)
    TextView mainViewTitle;
    @BindView(R.id.button)
    Button button;

    public PopularizeFragment() {
    }

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_popularize, container, false);
        }
        unbinder = ButterKnife.bind(this, view);
        mainViewTitle.setText("我要推广");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (mainViewTop != null)
                mainViewTop.setVisibility(View.VISIBLE);
        }
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick(R.id.button)
    public void onViewClicked() {

    }
}
