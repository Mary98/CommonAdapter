# CommonAdapter
全能的ListView和GridView适配器

支持多种样式的Item

引用：
Android Studio只需导入adapter_lib作为module

Eclipse 需要自行添加代码

类型一、

普通Item布局推荐直接使用CommonAdapter做适配

        setListAdapter(new CommonAdapter<Bean>(getActivity(), mDatas, R.layout.item_single_listview) {

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

类型二、

多样式ListView 布局(类似微信聊天框)

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
    
        super.onViewCreated(view, savedInstanceState);

        getListView().setDivider(null);// 去掉间隔
        
        setListAdapter(new ChatAdapter(getActivity(), mDatas));

    }
    
    public static class ChatAdapter extends MultiItemCommonAdapter<ChatMessage> {

        public ChatAdapter(Context context, List<ChatMessage> datas) {
            super(context, datas, new MultiItemTypeSupport<ChatMessage>() {
                @Override
                public int getLayoutId(int position, ChatMessage chatMessage) {
                    if (chatMessage.isComMeg())
                        return R.layout.main_chat_from_msg;
                    else
                        return R.layout.main_chat_send_msg;
                }

                @Override
                public int getViewTypeCount() {
                    return 2;
                }

                @Override
                public int getItemViewType(int position, ChatMessage chatMessage) {
                    if (chatMessage.isComMeg())
                        return ChatMessage.RECIEVE_MSG;
                    else
                    return ChatMessage.SEND_MSG;
                }
            });
        }

        @Override
        public void convert(ViewHolder holder, ChatMessage chatMessage) {
            /**
             * 判断 Item 布局，再设置数据
             */
            switch (holder.getLayoutId()) {
                case R.layout.main_chat_from_msg:
                    holder.setText(R.id.chat_from_content, chatMessage.getContent());
                    holder.setText(R.id.chat_from_name, chatMessage.getName());
                    holder.setImageResource(R.id.chat_from_icon, chatMessage.getIcon());
                    break;
                case R.layout.main_chat_send_msg:
                    holder.setText(R.id.chat_send_content, chatMessage.getContent());
                    holder.setText(R.id.chat_send_name, chatMessage.getName());
                    holder.setImageResource(R.id.chat_send_icon, chatMessage.getIcon());
                    break;
            }
        }
    }
   
类型三、

ListView和GridView嵌套时(使用ScrollView)

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



