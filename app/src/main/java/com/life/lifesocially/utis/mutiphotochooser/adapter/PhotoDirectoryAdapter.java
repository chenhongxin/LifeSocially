package com.life.lifesocially.utis.mutiphotochooser.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.life.lifesocially.R;
import com.life.lifesocially.utis.adapter.BaseItemAdapter;
import com.life.lifesocially.utis.mutiphotochooser.model.ImageBucketItem;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * 
 */
public class PhotoDirectoryAdapter extends BaseItemAdapter {

    public PhotoDirectoryAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.adapter_photo_directory, null);
            holder.contentImage = (ImageView) view.findViewById(R.id.content_image);
            holder.contentInfoText = (TextView) view.findViewById(R.id.content_info_text);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        ImageBucketItem bucketItem = (ImageBucketItem) dataList.get(i);
        holder.contentInfoText.setText(bucketItem.directoryName + "( " + bucketItem.imageItemArrayList.size() + ")");

        if (bucketItem.imageItemArrayList.size() > 0) {
            ImageLoader.getInstance().displayImage("file://" + bucketItem.imageItemArrayList.get(0).imagePath, holder.contentImage);
        } else {
            ImageLoader.getInstance().displayImage("", holder.contentImage);
        }
        return view;
    }

    static class ViewHolder {
        ImageView contentImage;
        TextView contentInfoText;
    }
}