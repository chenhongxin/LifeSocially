package com.life.lifesocially.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.life.lifesocially.R;

import java.util.ArrayList;
import java.util.List;

public abstract class PetSaleCommonActivity extends BaseActivity {

	protected FragmentManager manager;
	protected FragmentTransaction transaction;
	protected List<Fragment> fragments = new ArrayList<Fragment>();
	protected RadioGroup radio_group;
	
	@Override
	protected void onCreate(Bundle onInstanceBundle) {
		super.onCreate(onInstanceBundle);
		initFragment();
		initFragmentControl();
		initFragmentEvent();
	}

	private void initFragmentEvent() {
		radio_group.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
//				transaction = manager.beginTransaction();
//				for (Fragment fragment : fragments) {
//					transaction.hide(fragment);
//				}
				onCheckedChangeListener(group, checkedId);
			}
		});
	}

	private void initFragmentControl() {
		radio_group = (RadioGroup) findViewById(R.id.radio_group);
		initData();
	}

	private void initFragment() {
//		manager = getSupportFragmentManager();
//		transaction = manager.beginTransaction();
//		fragments.add(manager.findFragmentById(R.id.f_index_group));
//		fragments.add(manager.findFragmentById(R.id.f_index_adopt));
//		fragments.add(manager.findFragmentById(R.id.f_index_course));
//		for (Fragment fragment : fragments) {
//			transaction.hide(fragment);
//		}
//		transaction.show(fragments.get(0)).commit();
	}
	public abstract void onCheckedChangeListener(RadioGroup group, int checkedId);
	public abstract void initData();
	
}