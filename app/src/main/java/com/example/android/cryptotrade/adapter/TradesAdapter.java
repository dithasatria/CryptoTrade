package com.example.android.cryptotrade.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.cryptotrade.R;
import com.example.android.cryptotrade.pojo.Trades;
import com.example.android.cryptotrade.viewholder.TradesViewHolder;

/**
 * Created by DITHA on 16/07/2017.
 */

public class TradesAdapter extends RecyclerView.Adapter<TradesViewHolder> {

    Trades[] trades;
    Activity activity;

    public TradesAdapter(Trades[] trades, Activity activity) {
        this.trades = trades;
        this.activity = activity;
    }

    @Override
    public TradesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vh = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_tab_trades, parent, false);
        return new TradesViewHolder(vh);
    }

    @Override
    public void onBindViewHolder(TradesViewHolder holder, int position) {
        holder.bind(trades[position]);
    }

    @Override
    public int getItemCount() {
        return trades.length;
    }

}
