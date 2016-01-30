package com.mary.adapter_lib;

/**
 * Created by Mary on 16/1/30.
 */
public interface MultiItemTypeSupport<T> {
    /**指定的Item使用指定的布局文件*/
    int getLayoutId(int position, T t);
    /**Item布局类型数*/
    int getViewTypeCount();
    /**指定使用的Item类型*/
    int getItemViewType(int position, T t);

}
