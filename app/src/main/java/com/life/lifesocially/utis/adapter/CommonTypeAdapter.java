package com.life.lifesocially.utis.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by chenhongxinxiangya on 2016/3/30.
 */
public abstract class CommonTypeAdapter<T> extends BaseAdapter {
    protected LayoutInflater layoutInflater;
    protected List<T> mDatas;
    protected Context context;

    public CommonTypeAdapter(Context context, List<T> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
        layoutInflater = LayoutInflater.from(context);
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
        int type = getItemViewType(position);
        ViewHolder viewHolder = ViewHolder.get(context, parent, convertView,
                getLayoutId(type), position);
        viewHolder.setmPosition(position);
        convert(viewHolder, mDatas.get(position));
        return viewHolder.getConvertView();
    }

    @Override
    public int getItemViewType(int position) {
        return getItemType(position);
    }

    @Override
    public int getViewTypeCount() {
        return getTypeCount();
    }

    public abstract int getTypeCount();

    public abstract int getItemType(int position);

    public abstract void convert(ViewHolder helper, T item);

    public abstract int getLayoutId(int type);

}
