package com.asuper.aidldemo.view;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

/**
 * Created by Super on 2017/2/22.
 */

public class RecyclerItem extends ItemTouchHelper.Callback {


    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        //支持上下拖动
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;

        //支持左右滑动
        int swipeFlag = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;

        int flags = makeMovementFlags(dragFlags, swipeFlag);
        return flags;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        Log.i("DMC", "onMove");
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        Log.i("DMC", "onSwiped");
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        Log.i("cmd", dX + " " + dY + " " + actionState);
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }
}
