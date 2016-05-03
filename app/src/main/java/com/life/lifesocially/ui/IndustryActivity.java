package com.life.lifesocially.ui;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;

import com.google.gson.internal.LinkedTreeMap;
import com.life.lifeconnect.LifeConnect;
import com.life.lifeconnect.LifeResponse;
import com.life.lifeconnect.LifeResultResponseHandler;
import com.life.lifesocially.R;
import com.life.lifesocially.base.BaseTitleActivity;
import com.life.lifesocially.model.RecyclerBean;
import com.life.lifesocially.ui.adapter.JobFunctionAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;

public class IndustryActivity extends BaseTitleActivity {

    @Bind(R.id.elv_sort_all)
    ExpandableListView elv_sort_all;
    private List<RecyclerBean> sortAllBeans = new ArrayList<RecyclerBean>();
    private AdapterView.OnItemClickListener mOnItemClickListener;

    @Override
    public int getContentViewID() {
        return R.layout.activity_industry;
    }

    @Override
    public void initData() {
        params.clear();
        progressDialog.startProgressDialog();
        LifeConnect.getInstance().call("/app/getAllIndustry", params, new LifeResultResponseHandler() {
            @Override
            public void onSuccess(LifeResponse lifeListResponse) {
                progressDialog.stopProgressDialog();
                ArrayList<HashMap<String, Object>> industrys = (ArrayList<HashMap<String, Object>>) lifeListResponse.data;
                for (HashMap<String, Object> industry : industrys) {
                    List<RecyclerBean> recyclerBeans = new ArrayList<RecyclerBean>();
                    List<LinkedTreeMap<String, Object>> childSecondOptions = (List<LinkedTreeMap<String, Object>>) industry.get("sonClass");
                    for (LinkedTreeMap<String, Object> o : childSecondOptions) {
                        List<RecyclerBean> thirdBeans = new ArrayList<RecyclerBean>();
                        List<LinkedTreeMap<String, Object>> childThirdOptions = (List<LinkedTreeMap<String, Object>>) o.get("label");
                        for (LinkedTreeMap<String, Object> option : childThirdOptions) {
                            thirdBeans.add(new RecyclerBean().setId(option.get("labelId") + "").setTitle(option.get("labelName") + ""));
                        }
                        recyclerBeans.add(new RecyclerBean().setId(o.get("") + "").setTitle(o.get("sonName") + "").setRecyclerBeans(thirdBeans));
                    }
                    sortAllBeans.add(new RecyclerBean().setId(industry.get("parentId") + "").setTitle(industry.get("parentName") + "").setRecyclerBeans(recyclerBeans));
                }
                mOnItemClickListener = new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {

                    }
                };
                JobFunctionAdapter mMainAdapter = new JobFunctionAdapter(IndustryActivity.this, mOnItemClickListener, sortAllBeans, elv_sort_all) {
                    @Override
                    public void onListItemClick(String id, String title) {
                        showToast(id + ":" + title);
                    }
                };
                elv_sort_all.setAdapter(mMainAdapter);
            }

            @Override
            public void onFail(String error) {
                showToast(error);
                progressDialog.stopProgressDialog();
            }
        });
    }

    @Override
    public void initWidgetClick() {

    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void initWidget() {
        super.initWidget();
        setTitle("选择行业方向");
        elv_sort_all.setGroupIndicator(null);
    }
}
