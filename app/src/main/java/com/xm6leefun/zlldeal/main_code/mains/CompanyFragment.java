package com.xm6leefun.zlldeal.main_code.mains;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xm6leefun.zlldeal.R;
import com.xm6leefun.zlldeal.adapter.CompanyAdapter;
import com.xm6leefun.zlldeal.base.BaseFragment;
import com.xm6leefun.zlldeal.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
/**
 * A simple {@link Fragment} subclass.
 */
public class CompanyFragment extends BaseFragment {
    Unbinder unbinder;
    @BindView(R.id.main_view_top)
    View mainViewTop;
    @BindView(R.id.main_view_title)
    TextView mainViewTitle;
    @BindView(R.id.new_recyc_list)
    RecyclerView mRecyclerView;
    public CompanyFragment() {
    }
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_company, container, false);
        }
        unbinder = ButterKnife.bind(this, view);
        mainViewTitle.setText("企业");
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        initAdapter();
        return view;
    }
    private void initAdapter() {
       List<String> mList = new ArrayList<>();
        for (int i= 0;i<=29;i++)
        {
            mList.add("");
        }
        CompanyAdapter newMainAdapter = new CompanyAdapter(getActivity(),mList);
        mRecyclerView.setAdapter(newMainAdapter);
        newMainAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtil.show("第"+(position+1)+"个");
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
