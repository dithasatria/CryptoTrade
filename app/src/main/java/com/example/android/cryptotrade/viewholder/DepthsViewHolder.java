package com.example.android.cryptotrade.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.android.cryptotrade.R;

import java.text.DecimalFormat;
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
        Double sellGet0 = Double.parseDouble(sell.get(0));
        Double sellGet1 = Double.parseDouble(sell.get(1));
        Double buyGet0 = Double.parseDouble(buy.get(0));
        Double buyGet1 = Double.parseDouble(buy.get(1));

        DecimalFormat format = new DecimalFormat("0.00000");
        DecimalFormat formatPrice = new DecimalFormat("0.00000000");
        TV_BUY_PRICE.setText(formatPrice.format(sellGet0));
        TV_BUY_SUM.setText(format.format(sellGet1));
        TV_SELL_PRICE.setText(formatPrice.format(buyGet0));
        TV_SELL_SUM.setText(format.format(buyGet1));
    }
}
