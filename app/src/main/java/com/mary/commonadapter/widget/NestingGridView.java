package com.mary.commonadapter.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by Mary on 16/1/30.
 */
public class NestingGridView extends GridView {
    public NestingGridView(Context context) {
        this(context, null);
    }

    public NestingGridView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NestingGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}
