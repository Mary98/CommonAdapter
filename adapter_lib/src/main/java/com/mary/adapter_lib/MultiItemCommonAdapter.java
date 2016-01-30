package com.mary.adapter_lib;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Mary on 16/1/30.
 */
public abstract class MultiItemCommonAdapter<T> extends CommonAdapter<T> {

    protected MultiItemTypeSupport<T> mMultiItemTypeSupport;

    public MultiItemCommonAdapter(Context context, List<T> datas, MultiItemTypeSupport<T> multiItemTypeSupport) {
        super(context, datas, -1);
        this.mMultiItemTypeSupport = multiItemTypeSupport;
    }

    @Override
    public int getItemViewType(int position) {
        if (null != mMultiItemTypeSupport)
            return mMultiItemTypeSupport.getItemViewType(position,mDatas.get(position));
        return super.getItemViewType(position);
    }

    @Override
    public int getViewTypeCount() {
        if (null != mMultiItemTypeSupport)
            return mMultiItemTypeSupport.getViewTypeCount();
        return super.getViewTypeCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (null == mMultiItemTypeSupport)
            return super.getView(position, convertView, parent);

        int layoutId = mMultiItemTypeSupport.getLayoutId(position, (T) getItem(position));
        ViewHolder viewHolder = ViewHolder.get(mContext, convertView, parent, layoutId, position);
        convert(viewHolder, (T) getItem(position));
        return viewHolder.getConvertView();
    }
}
