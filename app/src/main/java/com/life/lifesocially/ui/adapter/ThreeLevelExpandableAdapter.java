package com.life.lifesocially.ui.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ListView;

import com.life.lifesocially.R;
import com.life.lifesocially.utis.ScreenUtils;
import com.life.lifesocially.utis.adapter.CommonAdapter;
import com.life.lifesocially.utis.adapter.ViewHolder;

/**
 * 三级展开
 */
public abstract class ThreeLevelExpandableAdapter extends
        BaseExpandableListAdapter {

    public Context mContext;
    private AdapterView.OnItemClickListener mListener;
    private int mThreeLevelColumns = 1;
    protected int childSelectedId = -1;
    protected int groupSelectId = -1;
    int screentWidth;
    int screentHeight;

    public class CustExpListview extends ExpandableListView {

        public CustExpListview(Context context) {
            super(context);
        }

        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                    MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    public ThreeLevelExpandableAdapter(Context context,
                                       AdapterView.OnItemClickListener listener) {
        mContext = context;
        mListener = listener;
        screentWidth = ScreenUtils.getScreenWidth(context);
        screentHeight = ScreenUtils.getScreenHeight(context);
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final CustExpListview SecondLevelexplv = new CustExpListview(mContext);
        final ChildExpandableListAdapter carStyleAdapter = new ChildExpandableListAdapter(
                groupPosition, childPosition, SecondLevelexplv);
        SecondLevelexplv.setAdapter(carStyleAdapter);
        SecondLevelexplv.setGroupIndicator(null);
        SecondLevelexplv.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPositions) {
                onGroupExpandChild(groupPosition, childPosition, carStyleAdapter);
            }
        });
        SecondLevelexplv.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                if (childPosition == childSelectedId) {
                    childSelectedId = -1;
                    notifyDataSetChanged();
                    carStyleAdapter.notifyDataSetChanged();
                }
            }
        });
        if (childSelectedId == childPosition && groupPosition == groupSelectId) {
            SecondLevelexplv.expandGroup(childPosition);
        } else {
            SecondLevelexplv.collapseGroup(childPosition);
        }
        return SecondLevelexplv;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    protected class ChildExpandableListAdapter extends BaseExpandableListAdapter {
        private int mFatherGroupPosition, mChildGroupPosition;
        private CustExpListview secondLevelexplv;

        public ChildExpandableListAdapter(int groupPosition, int childPosition, CustExpListview secondLevelexplv) {
            mFatherGroupPosition = groupPosition;
            mChildGroupPosition = childPosition;
            this.secondLevelexplv = secondLevelexplv;
        }

        @Override
        public int getGroupCount() {
            return 1;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return 1;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return ThreeLevelExpandableAdapter.this.getChild(
                    mFatherGroupPosition, groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return getGrandChild(mFatherGroupPosition, groupPosition,
                    childPosition);
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
        public View getGroupView(int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent) {
            return getSecondLevleView(mFatherGroupPosition,
                    mChildGroupPosition, isExpanded, convertView, parent, secondLevelexplv);
        }

        @Override
        public View getChildView(final int groupPosition, final int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder(mContext, parent, R.layout.listview_layout, childPosition);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            ListView listView = viewHolder.getView(R.id.lf_listview);
            listView.setAdapter(new GridAdapter());
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    onListItemClick(position);
                }
            });
            return viewHolder.getConvertView();
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

        private class GridAdapter extends BaseAdapter {
            @Override
            public int getCount() {
                return getThreeLevelCount(mFatherGroupPosition,
                        mChildGroupPosition);
            }

            @Override
            public Object getItem(int position) {
                return getGrandChild(mFatherGroupPosition, mChildGroupPosition,
                        position);
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                return getThreeLevleView(mFatherGroupPosition,
                        mChildGroupPosition, position, convertView, parent);
            }

        }

    }

    public abstract int getThreeLevelCount(int firstLevelPosition,
                                           int secondLevelPosition);

    public abstract View getSecondLevleView(int firstLevelPosition,
                                            int secondLevelPosition, boolean isExpanded, View convertView,
                                            ViewGroup parent, CustExpListview secondLevelexplv);

    public abstract View getThreeLevleView(int firstLevelPosition,
                                           int secondLevelPosition, int ThreeLevelPosition, View convertView,
                                           ViewGroup parent);

    public abstract Object getGrandChild(int groupPosition, int childPosition,
                                         int grandChildPosition);

    public abstract void onGroupExpandChild(int groupPosition, int childPosition, ChildExpandableListAdapter carStyleAdapter);

    public abstract void onListItemClick(int position);

}