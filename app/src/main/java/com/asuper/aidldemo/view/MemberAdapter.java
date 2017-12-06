package com.asuper.aidldemo.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.asuper.aidldemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Super on 2017/3/8.
 */

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.ViewHolder> {
    private volatile List<Integer> mList = new ArrayList<Integer>();

    public void addList(List<Integer> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_room_menber, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public int getItemCount() {
        if (mList != null && mList.size() > 0) {
            return mList.size();
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
            holder.imageview.setBackgroundResource(mList.get(position));
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageview;

        public ViewHolder(View itemView) {
            super(itemView);
            imageview = (ImageView) itemView.findViewById(R.id.id_image_view);
        }
    }
}
