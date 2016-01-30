package com.mary.commonadapter.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by Mary on 16/1/30.
 */
public class NestingListView extends ListView {

    public NestingListView(Context context) {
        this(context, null);
    }

    public NestingListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NestingListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
