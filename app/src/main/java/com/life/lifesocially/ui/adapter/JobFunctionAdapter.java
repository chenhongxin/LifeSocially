package com.life.lifesocially.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.life.lifesocially.R;
import com.life.lifesocially.model.RecyclerBean;
import com.life.lifesocially.utis.adapter.ViewHolder;

import java.util.List;

/**
 * Created by chenhongxinxiangya on 2016/4/14.
 */
public abstract class JobFunctionAdapter extends ThreeLevelExpandableAdapter {

    private List<RecyclerBean> sortAllBeans;
    private int selectId = -1;
    private ExpandableListView elv_sort_all;

    public JobFunctionAdapter(Context context, AdapterView.OnItemClickListener litener, List<RecyclerBean> sortAllBeans, ExpandableListView elv_sort_all) {
        super(context, litener);
        this.sortAllBeans = sortAllBeans;
        this.elv_sort_all = elv_sort_all;
    }

    @Override
    public int getGroupCount() {
        return sortAllBeans.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return sortAllBeans.get(groupPosition).getRecyclerBeans().size();
    }

    @Override
    public RecyclerBean getGroup(int groupPosition) {
        return sortAllBeans.get(groupPosition);
    }

    @Override
    public RecyclerBean getChild(int groupPosition, int childPosition) {
        return sortAllBeans.get(groupPosition).getRecyclerBeans().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        RecyclerBean firstBean = sortAllBeans.get(groupPosition);
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder(mContext, parent, R.layout.sort_job_first, groupPosition);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        CheckBox rb_sort_job_first_title = viewHolder.getView(R.id.rb_sort_job_first_title);
        rb_sort_job_first_title.setText(firstBean.getTitle());
        final ImageView iv_sort_job_first_right = viewHolder.getView(R.id.iv_sort_job_first_right);
        final ImageView iv_sort_job_first_down = viewHolder.getView(R.id.iv_sort_job_first_down);
        rb_sort_job_first_title.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    iv_sort_job_first_right.setVisibility(View.GONE);
                    iv_sort_job_first_down.setVisibility(View.VISIBLE);
                } else {
                    iv_sort_job_first_right.setVisibility(View.VISIBLE);
                    iv_sort_job_first_down.setVisibility(View.GONE);
                }
            }
        });
        rb_sort_job_first_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < sortAllBeans.size(); i++) {
                    elv_sort_all.collapseGroup(i);
                }
                CheckBox rb_sort_job_first_title = (CheckBox) v;
                selectId = groupPosition;
                if (!rb_sort_job_first_title.isChecked()) {
                    selectId = -1;
                } else {
                    elv_sort_all.expandGroup(selectId);
                }
                notifyDataSetChanged();
            }
        });
        if (groupPosition == selectId) {
            rb_sort_job_first_title.setChecked(true);
        } else {
            rb_sort_job_first_title.setChecked(false);
        }
        return viewHolder.getConvertView();
    }

    @Override
    public int getThreeLevelCount(int firstLevelPosition,
                                  int secondLevelPosition) {
        return getGroup(firstLevelPosition).getRecyclerBeans().get(secondLevelPosition).getRecyclerBeans()
                .size();
    }

    @Override
    public View getSecondLevleView(final int firstLevelPosition,
                                   final int secondLevelPosition, boolean isExpanded, View convertView,
                                   ViewGroup parent, final ThreeLevelExpandableAdapter.CustExpListview secondLevelexplv) {
        final RecyclerBean firstBean = sortAllBeans.get(firstLevelPosition).getRecyclerBeans().get(secondLevelPosition);
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder(mContext, parent, R.layout.sort_job_third, firstLevelPosition);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final ImageView iv_sort_job_third_right = viewHolder.getView(R.id.iv_sort_job_third_right);
        final ImageView iv_sort_job_third_down = viewHolder.getView(R.id.iv_sort_job_third_down);
        TextView tv_sort_job_third_title = viewHolder.getView(R.id.tv_sort_job_third_title);
        tv_sort_job_third_title.setText(firstBean.getTitle());
        if (secondLevelPosition == childSelectedId) {
            iv_sort_job_third_right.setVisibility(View.GONE);
            iv_sort_job_third_down.setVisibility(View.VISIBLE);
            tv_sort_job_third_title.setTextColor(mContext.getResources().getColor(R.color.topViewColor));
        } else {
            iv_sort_job_third_right.setVisibility(View.VISIBLE);
            iv_sort_job_third_down.setVisibility(View.GONE);
            tv_sort_job_third_title.setTextColor(mContext.getResources().getColor(R.color.czp_5d5d5d));
        }
        return viewHolder.getConvertView();
    }

    @Override
    public View getThreeLevleView(int firstLevelPosition,
                                  int secondLevelPosition, int ThreeLevelPosition,
                                  View convertView, ViewGroup parent) {
        RecyclerBean fourthBean = sortAllBeans.get(firstLevelPosition).getRecyclerBeans().get(secondLevelPosition).getRecyclerBeans().get(ThreeLevelPosition);
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder(mContext, parent, R.layout.sort_job_fourth, secondLevelPosition);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        TextView tv_sort_job_fourth_title = viewHolder.getView(R.id.tv_sort_job_fourth_title);
        tv_sort_job_fourth_title.setText(fourthBean.getTitle());
        return viewHolder.getConvertView();
    }

    @Override
    public RecyclerBean getGrandChild(int groupPosition, int childPosition,
                                      int grandChildPosition) {
        return getChild(groupPosition, childPosition).getRecyclerBeans()
                .get(grandChildPosition);
    }

    @Override
    public void onGroupExpandChild(int groupPosition, int childPosition, ThreeLevelExpandableAdapter.ChildExpandableListAdapter carStyleAdapter) {
        childSelectedId = childPosition;
        groupSelectId = groupPosition;
        carStyleAdapter.notifyDataSetChanged();
        notifyDataSetChanged();
    }

    @Override
    public void onListItemClick(int position) {
        String id = sortAllBeans.get(groupSelectId).getRecyclerBeans().get(childSelectedId).getRecyclerBeans().get(position).getId();
        String title = sortAllBeans.get(groupSelectId).getRecyclerBeans().get(childSelectedId).getRecyclerBeans().get(position).getTitle();
        onListItemClick(id, title);
    }

    public abstract void onListItemClick(String id, String title);

}