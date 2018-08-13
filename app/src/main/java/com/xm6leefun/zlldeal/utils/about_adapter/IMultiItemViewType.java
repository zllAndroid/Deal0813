package com.xm6leefun.zlldeal.utils.about_adapter;

public interface IMultiItemViewType<T> {
	  int getViewTypeCount();
	  int getItemViewType(int position, T t);
	  int getLayoutId(int viewType);
}
