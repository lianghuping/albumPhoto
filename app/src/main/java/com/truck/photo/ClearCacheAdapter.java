package com.truck.photo;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.text.DecimalFormat;
import java.util.List;

public class ClearCacheAdapter extends BaseQuickAdapter<ClearCacheBean, BaseViewHolder> {

    private final Context context;
    private List<ClearCacheBean> data;

    public ClearCacheAdapter(int layoutResId, List<ClearCacheBean> data, Context context) {
        super(layoutResId, data);
        this.data = data;
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ClearCacheBean item) {
        helper.setText(R.id.textview_item_clearcache_name, item.getAppname());
        helper.setText(R.id.textview_item_clearcache_cache, formatFileSize(item.getSize()));
        ImageView view = helper.itemView.findViewById(R.id.imageview_item_clearcache);
        ImageView imageView2 = helper.itemView.findViewById(R.id.imageview2_item_clearcache);
        Glide.with(context).load(item.getAppicon()).into(view);

    }


    /**
     * 转换文件大小
     *
     * @param fileS
     * @return
     */
    public String formatFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.0");
        String fileSizeString = "";
        String wrongSize = "0B";
        if (fileS == 0) {
            return wrongSize;
        }
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "GB";
        }
        return fileSizeString;
    }

}
