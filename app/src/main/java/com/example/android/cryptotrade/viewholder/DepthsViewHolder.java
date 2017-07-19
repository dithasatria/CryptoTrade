package com.example.android.cryptotrade.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.android.cryptotrade.R;

import java.util.List;

/**
 * Created by DITHA on 17/07/2017.
 */

public class DepthsViewHolder extends RecyclerView.ViewHolder {

    private TextView TV_BUY_PRICE, TV_BUY_SUM, TV_SELL_PRICE, TV_SELL_SUM;

    public DepthsViewHolder(View itemView) {
        super(itemView);

        TV_BUY_PRICE = (TextView) itemView.findViewById(R.id.tvPriceBUYDepth);
        TV_BUY_SUM = (TextView) itemView.findViewById(R.id.tvSUMBUYDepth);
        TV_SELL_PRICE = (TextView) itemView.findViewById(R.id.tvPriceSELLDepth);
        TV_SELL_SUM = (TextView) itemView.findViewById(R.id.tvSUMSELLDepth);
    }

    public void bind(List<String> buy, List<String> sell){
        TV_BUY_PRICE.setText(sell.get(0));
        TV_BUY_SUM.setText(sell.get(1));
        TV_SELL_PRICE.setText(buy.get(0));
        TV_SELL_SUM.setText(buy.get(1));
    }
}
