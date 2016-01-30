package com.mary.commonadapter.fragment;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.Toast;

import com.mary.adapter_lib.CommonAdapter;
import com.mary.commonadapter.R;
import com.mary.adapter_lib.ViewHolder;
import com.mary.commonadapter.bean.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 普通Item布局ListView(没有嵌套)
 * Created by Mary on 16/1/30.
 */
public class SingleItemTypeFragment extends ListFragment {

    private List<Bean> mDatas;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDatas();// 初始化数据
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /**
         * 普通Item布局推荐直接使用CommonAdapter做适配
         * 参数：
         * context  -> 上下文对象
         * Datas    -> 数据
         * LayoutId -> Item 布局
         */
        setListAdapter(new CommonAdapter<Bean>(getActivity(), mDatas,
                R.layout.item_single_listview) {
            @Override
            public void convert(ViewHolder holder, final Bean bean) {
                // 设置Item显示
                holder.setText(R.id.id_title, bean.getTitle())
                        .setText(R.id.id_desc, bean.getDesc())
                        .setText(R.id.id_time, bean.getTime())
                        .setText(R.id.id_phone, bean.getPhone());

                // Item 子控件点击事件(设置多个子控件时需重新传参优化中...)
                holder.setOnClickListener(R.id.id_title, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getActivity(), bean.getTitle(), Toast.LENGTH_SHORT).show();
                    }
                });

                // Item 子控件点击事件
                holder.setOnClickListener(R.id.id_phone, new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), bean.getPhone(), Toast.LENGTH_SHORT).show();
                    }
                });

                // Item 点击事件
                holder.getConvertView().setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), "我是:" + bean.getTitle(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });

    }

    /**
     * 初始化数据
     */
    private void initDatas() {
        mDatas = new ArrayList<>();
        Bean bean;
        for (int i = 0; i < 10; i++) {
            bean = new Bean("Mary:" + i, "全能ListView和GridView适配器", "2015-01-30", "10086");
            mDatas.add(bean);
        }
    }
}
