package com.life.lifesocially.utis.adapter;

import java.io.Serializable;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder implements Serializable {

	private static final long serialVersionUID = 1L;

	private final SparseArray<View> mViews;
	private int mPosition;
	private View mConvertView;

	public ViewHolder(Context context, ViewGroup parent, int layoutId,
					   int position) {
		this.mViews = new SparseArray<View>();
		mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,
				false);
		mConvertView.setTag(this);
	}

	/**
	 * 获取ViewHolder存储内存的对象
	 *
	 * @param context
	 *            上下文
	 * @param parent
	 *            组的父类
	 * @param convertView
	 *            转成view
	 * @param layoutId
	 *            布局
	 * @param position
	 *            位置
	 * @return
	 */
	public static ViewHolder get(Context context, ViewGroup parent,
								 View convertView, int layoutId, int position) {

		if (convertView == null) {
			return new ViewHolder(context, parent, layoutId, position);
		}
		return (ViewHolder) convertView.getTag();
	}

	/**
	 * 通过控件的Id获取对于的控件，如果没有则加入views
	 *
	 * @param viewId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends View> T getView(int viewId) {
		View view = mViews.get(viewId);
		if (view == null) {
			view = mConvertView.findViewById(viewId);
			mViews.put(viewId, view);
		}
		return (T) view;
	}


	public ViewHolder setText(int viewId, String text) {
		TextView textView = getView(viewId);
		textView.setText(text);
		return this;
	}

	public ViewHolder setImageResource(int viewId, int resId) {
		ImageView imageView = getView(viewId);
		imageView.setImageResource(resId);
		return this;
	}

	public ViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
		ImageView imageView = getView(viewId);
		imageView.setImageBitmap(bitmap);
		return this;
	}

	public interface LoadImage {
		public void load(Bitmap dest);
	}

	public View getConvertView() {
		return mConvertView;
	}

	public int getPosition() {
		return mPosition;
	}

	public void setmPosition(int mPosition) {
		this.mPosition = mPosition;
	}

}