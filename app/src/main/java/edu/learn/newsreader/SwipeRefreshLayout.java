package edu.learn.newsreader;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

class SwipeRefreshLayout extends View {
    public SwipeRefreshLayout(Context context) {
        this(context, null);
    }

    public SwipeRefreshLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwipeRefreshLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}
