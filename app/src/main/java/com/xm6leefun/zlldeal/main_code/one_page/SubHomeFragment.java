package com.xm6leefun.zlldeal.main_code.one_page;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xm6leefun.model.DataHomeList;
import com.xm6leefun.model.DataHomeTop;
import com.xm6leefun.zlldeal.R;
import com.xm6leefun.zlldeal.adapter.HomeAdapter;
import com.xm6leefun.zlldeal.base.BaseFragment;
import com.xm6leefun.zlldeal.base.CommonParameter;
import com.xm6leefun.zlldeal.base.URL_GET;
import com.xm6leefun.zlldeal.main_code.about_kchar.KkkActivity;
import com.xm6leefun.zlldeal.main_code.mains.MainActivity;
import com.xm6leefun.zlldeal.utils.IntentUtils;
import com.xm6leefun.zlldeal.utils.NetWorkUtlis;
import com.xm6leefun.zlldeal.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class SubHomeFragment extends BaseFragment
{
	//	private RefreshLayout mRefreshView;
//	private RefreshLayout mRefreshTwo;
	int tiao = 20 ;
	RecyclerView mRecyclerview;
	int position;
	String text ;
	String id;
	boolean isHave = true;
	List<DataHomeList.RecordBean.TicketListBean> mList = new ArrayList<>();
	int page = 1;
	DataHomeList.RecordBean record=null;
	private View view;
	private LayoutInflater inflater;
	private BaseAdapter mAdapter;
	private ListView mListview_One;
	private ListView mlv_two;
	private BaseAdapter twoAdapter;
	private int count;
	private int countTwo;
	//	@BindView(R.id.subhome_text)
//	TextView mTvText;
	SwipeRefreshLayout mRefreshLayout;
	public SubHomeFragment()
	{
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		this.inflater = inflater;
		if (view == null)
		{
			view = inflater.inflate(R.layout.fragment_subhome1, container, false);
			//获取左右滑动的position，判断显示的界面。不同布局
			Bundle bundle = getArguments();
			position =bundle.getInt("position");
			text =(String)bundle.get("text");
			id =(String)bundle.get("id");
			mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
			mRecyclerview =(RecyclerView) view.findViewById(R.id.per_already_recyc);
			mRecyclerview.setHasFixedSize(true);
			mRecyclerview.setNestedScrollingEnabled(false);
			mRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
			mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
				@Override
				public void onRefresh() {
					ToastUtil.show("下拉刷新成功");
//					if (mRefreshLayout.isRefreshing())
//					{
//					}
					isHave = true;
					initHttpData();
					mRefreshLayout.setRefreshing(false);
				}
			});
			isHave = true;
			initHttpData();
			UpLoding();
		}
		return view;
	}

	public void UpLoding() {
		mRecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
			@Override
			public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
				super.onScrollStateChanged(recyclerView, newState);
				LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
				// 当不滑动时
				if (newState == RecyclerView.SCROLL_STATE_IDLE) {
					// 获取最后一个完全显示的itemPosition
					int lastItemPosition = manager.findLastCompletelyVisibleItemPosition();
					int itemCount = manager.getItemCount();
					// 判断是否滑动到了最后一个Item，并且是向左滑动
					if (lastItemPosition == (itemCount - 1)) {
						// 加载更多
						if ( record!=null) {
							if ( record.getPage() < record.getPages()) {
								page = record.getPage();
								page++;
								initHttpData();
							}else {
								if (mList.size()!=0)
									ToastUtil.show(getResources().getString(R.string.that_all));
							}
						}
					}
				}
			}
		});
	}

	private void initHttpData() {
		String ticketType ="3";
		NetWorkUtlis netWorkUtlis = new NetWorkUtlis();
		netWorkUtlis.setOnNetWork(CommonParameter.LodingFlower, URL_GET.ticketList(ticketType,page+"",id,"1","2"), new NetWorkUtlis.OnNetWork() {
			@Override
			public void onNetSuccess(String result) {

				DataHomeList dataHomeList = JSON.parseObject(result, DataHomeList.class);
				record = dataHomeList.getRecord();
                    List<DataHomeList.RecordBean.TicketListBean> activateList = record.getTicketList();
				if (activateList!=null)
				{
					if (isHave) {
						mList.clear();
						isHave = false;
					}
					mList.addAll(activateList);
				}
				initAdapter();
			}
		});


	}
	HomeAdapter homeAdapter = null;
	private void initAdapter() {
//        加线
		mRecyclerview.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
		homeAdapter = new HomeAdapter(mList);
		mRecyclerview.setAdapter(homeAdapter);
		homeAdapter.notifyDataSetChanged();
		homeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
			@Override
			public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//				DataAlreadyJiHuo.RecordBean.ActivateListBean item = (DataAlreadyJiHuo.RecordBean.ActivateListBean)adapter.getItem(position);
////                ToastUtil.show(item.getWwiconTypeId());
//				String urltest = "http://trace.xm6leefun.com/bBrowser/index.html#/blockDetail/";
//				IntentUtils.JumpGoH5(getResources().getString(R.string.goods_list),urltest+item.getWwiconTypeId()+"?type=android");
			}
		});
	}

}