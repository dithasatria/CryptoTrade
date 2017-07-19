package com.example.android.cryptotrade.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.cryptotrade.R;
import com.example.android.cryptotrade.pojo.ListDepths;
import com.example.android.cryptotrade.viewholder.DepthsViewHolder;

/**
 * Created by DITHA on 16/07/2017.
 */

public class DepthsAdapter extends RecyclerView.Adapter<DepthsViewHolder>{
    ListDepths depthses;
    Activity activity;

    public DepthsAdapter(ListDepths depthses, Activity activity) {
        this.depthses = depthses;
        this.activity = activity;
    }

    @Override
    public DepthsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vh = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_tab_depths, parent, false);
        return new DepthsViewHolder(vh);
    }

    @Override
    public void onBindViewHolder(DepthsViewHolder holder, int position) {
        holder.bind(depthses.getBuy().get(position), depthses.getSell().get(position));
    }

    @Override
    public int getItemCount() {
        if(depthses.getBuy().size() > depthses.getSell().size()){
            return depthses.getBuy().size();
        } else{
            return depthses.getSell().size();
        }
    }
}
