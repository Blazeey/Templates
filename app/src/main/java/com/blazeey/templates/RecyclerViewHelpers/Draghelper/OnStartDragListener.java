package com.blazeey.templates.RecyclerViewHelpers.Draghelper;

import com.avazapp.autism.en.avaz.screens.Picture.PictureRecyclerAdapter;

/**
 * Listener for manual initiation of a drag.
 */
public interface OnStartDragListener {

    /**
     * Called when a view is requesting a start of a drag.
     *
     * @param viewHolder The holder of the view to drag.
     */
    void onStartDrag(PictureRecyclerAdapter.PictureViewHolder viewHolder);

}
