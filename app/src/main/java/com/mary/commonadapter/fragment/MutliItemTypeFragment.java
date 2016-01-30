package com.mary.commonadapter.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import com.mary.commonadapter.R;
import com.mary.adapter_lib.MultiItemCommonAdapter;
import com.mary.adapter_lib.MultiItemTypeSupport;
import com.mary.adapter_lib.ViewHolder;
import com.mary.commonadapter.bean.ChatMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * 多样式ListView 布局(类似微信聊天框)
 * Created by Mary on 16/1/30.
 */
public class MutliItemTypeFragment extends ListFragment {
    private ArrayList<ChatMessage> mDatas;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDatas();// 初始化数据
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getListView().setDivider(null);// 去掉间隔
        setListAdapter(new ChatAdapter(getActivity(), mDatas));

    }

    /**
     * 初始化数据
     */
    private void initDatas() {
        mDatas = new ArrayList<>();
        ChatMessage msg;
        for (int i = 0; i < 15; i++) {
            if ( 0 == (i % 2))
                msg = new ChatMessage(R.drawable.mary, "Mary", "I am Mary", null, false);
            else
                msg = new ChatMessage(R.drawable.demon, "Demon", "I am Demon", null, true);
            mDatas.add(msg);
        }

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
}
