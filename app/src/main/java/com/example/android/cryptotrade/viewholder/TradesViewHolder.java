package com.example.android.cryptotrade.viewholder;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.android.cryptotrade.R;
import com.example.android.cryptotrade.pojo.Trades;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by DITHA on 17/07/2017.
 */

public class TradesViewHolder extends RecyclerView.ViewHolder {

    private TextView TV_DATE, TV_TYPE, TV_PRICE, TV_AMOUNT;

    public TradesViewHolder(View itemView) {
        super(itemView);
        TV_DATE = (TextView) itemView.findViewById(R.id.tvTabDate) ;
        TV_TYPE = (TextView) itemView.findViewById(R.id.tvTabType);
        TV_PRICE = (TextView) itemView.findViewById(R.id.tvTabPrice);
        TV_AMOUNT = (TextView) itemView.findViewById(R.id.tvTabAmount);
    }

    public void bind(Trades data){
        if(data.getType().equals("buy")){
            TV_TYPE.setTextColor(Color.parseColor("#4CAF50"));
        }else{
            TV_TYPE.setTextColor(Color.parseColor("#F44336"));
        }

        Date date = new Date(Long.parseLong(data.getDate()) * 1000L);
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        Calendar cal = Calendar.getInstance();
        TimeZone tz = cal.getTimeZone();
        format.setTimeZone(tz);
        String formatted = format.format(date);

        TV_DATE.setText(formatted);
        TV_TYPE.setText(data.getType());
        TV_PRICE.setText(data.getPrice());
        TV_AMOUNT.setText(data.getAmount());
    }
}
