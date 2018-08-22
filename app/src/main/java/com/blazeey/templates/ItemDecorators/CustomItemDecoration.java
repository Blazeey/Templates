package com.blazeey.templates.ItemDecorators;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Item decoration for recyclerview items for spacing
 */
public class CustomItemDecoration extends RecyclerView.ItemDecoration {

    private final int verticalSpaceHeight;

    public CustomItemDecoration(int verticalSpaceHeight) {
        this.verticalSpaceHeight = verticalSpaceHeight;
    }

    /**
     * provides left and right spacing for each item in recyclerview
     * @param outRect the rectangle for the view
     * @param view the view itself
     * @param parent the recyclerview
     * @param state state of the recyclerview
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        outRect.right = verticalSpaceHeight;
        outRect.left = verticalSpaceHeight;
        if (parent.getChildAdapterPosition(view) != parent.getAdapter().getItemCount() - 1) {
            outRect.right = verticalSpaceHeight;
        }
        if (parent.getChildAdapterPosition(view) != 0) {
            outRect.left = verticalSpaceHeight;
        }
    }
}