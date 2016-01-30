package com.mary.commonadapter.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mary.commonadapter.R;
import com.mary.adapter_lib.CommonAdapter;
import com.mary.adapter_lib.ViewHolder;
import com.mary.commonadapter.bean.Bean;
import com.mary.commonadapter.widget.NestingGridView;
import com.mary.commonadapter.widget.NestingListView;

import java.util.ArrayList;
import java.util.List;

/**
 * ListView和GridView嵌套时(使用ScrollView)
 * Created by Mary on 16/1/30.
 */
public class NestingTypeFragment extends Fragment {
    private List<Bean> mDatas;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_nesting, container, false);
        initDatas();// 初始化数据
        initViews(rootView);// 初始化视图
        return rootView;
    }

    private void initViews(View rootView) {
        NestingListView listView = (NestingListView) rootView.findViewById(R.id.id_nlv_Item);
        NestingGridView gridView = (NestingGridView) rootView.findViewById(R.id.id_ngv_Item);

        listView.setAdapter(new CommonAdapter<Bean>(getActivity(), mDatas, R.layout.item_single_listview) {
            @Override
            public void convert(ViewHolder holder, final Bean bean) {
                // 支持链式设置(同一子控件设置属性需多次传参,优化中...)
                holder.setText(R.id.id_title, bean.getTitle())
                        .setText(R.id.id_desc, bean.getDesc())
                        .setText(R.id.id_time, bean.getTime())
                        .setText(R.id.id_phone, bean.getPhone())
                .setBackgroundColor(R.id.id_time, Color.BLUE);

                holder.setOnClickListener(R.id.id_title, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getActivity(), bean.getTitle(), Toast.LENGTH_SHORT).show();
                    }
                });

                holder.setOnClickListener(R.id.id_phone, new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), bean.getPhone(), Toast.LENGTH_SHORT).show();
                    }
                });

                holder.getConvertView().setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), "我是:" + bean.getTitle(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        gridView.setAdapter(new CommonAdapter<Bean>(getActivity(), mDatas, R.layout.item_single_listview) {

            @Override
            public void convert(ViewHolder holder, final Bean bean) {
                // 支持链式设置(同一子控件设置属性需多次传参,优化中...)
                holder.setText(R.id.id_title, bean.getTitle())
                        .setText(R.id.id_desc, bean.getDesc())
                        .setText(R.id.id_time, bean.getTime())
                        .setText(R.id.id_phone, bean.getPhone())
                .setTextColor(R.id.id_time, Color.RED);

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

    private void initDatas() {
        mDatas = new ArrayList<>();
        Bean bean;
        for (int i = 0; i < 10; i++) {
            bean = new Bean("Mary:" + i, "全能ListView和GridView适配器", "2015-01-30", "10086");
            mDatas.add(bean);
        }

    }

}
