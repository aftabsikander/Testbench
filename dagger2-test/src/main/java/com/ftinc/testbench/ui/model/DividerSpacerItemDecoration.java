package com.ftinc.testbench.ui.model;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;

public class DividerSpacerItemDecoration extends ItemDecoration {
    private boolean mDrawFirst = false;
    private float mHeight = -1.0F;

    public DividerSpacerItemDecoration(float height, boolean drawFirst) {
        this.mHeight = height;
        this.mDrawFirst = drawFirst;
    }

    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if(this.mHeight != -1.0F) {
            if(parent.getChildPosition(view) >= LinearLayoutManager.VERTICAL || this.mDrawFirst) {
                outRect.top = (int)this.mHeight;

                if(parent.getChildPosition(view) % 3 == 0) {
                    outRect.left = (int) this.mHeight;
                }

                outRect.right = (int)this.mHeight;

                if(parent.getChildPosition(view) == parent.getChildCount() - 1) {
                    outRect.bottom = (int)this.mHeight;
                }
            }
        }
    }

    public void onDrawOver(Canvas c, RecyclerView parent, State state) {
        super.onDrawOver(c, parent, state);
    }

    private int getOrientation(RecyclerView parent) {
        if(parent.getLayoutManager() instanceof LinearLayoutManager) {
            LinearLayoutManager layoutManager = (LinearLayoutManager)parent.getLayoutManager();
            return layoutManager.getOrientation();
        } else {
            throw new IllegalStateException("DividerItemDecoration can only be used with a LinearLayoutManager.");
        }
    }
}