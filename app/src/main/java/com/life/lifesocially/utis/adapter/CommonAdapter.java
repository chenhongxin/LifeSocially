package com.life.lifesocially.utis.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class CommonAdapter<T> extends BaseAdapter {

	protected LayoutInflater layoutInflater;
	protected List<T> mDatas;
	protected Context context;
	protected final int itemLayoutId;

	public CommonAdapter(Context context, List<T> mDatas, int itemLayoutId) {
		this.context = context;
		this.mDatas = mDatas;
		layoutInflater = LayoutInflater.from(context);
		this.itemLayoutId = itemLayoutId;
	}

	@Override
	public int getCount() {
		return mDatas.size();
	}

	@Override
	public Object getItem(int position) {
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = ViewHolder.get(context, parent, convertView,
				itemLayoutId, position);
		viewHolder.setmPosition(position);
		convert(viewHolder, mDatas.get(position));
		return viewHolder.getConvertView();
	}

	public abstract void convert(ViewHolder helper, T item);

}