package com.example.android.cryptotrade.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by DITHA on 15/07/2017.
 */

public class ListTicker {
    @SerializedName("high")
    private String priceHigh;

    @SerializedName("low")
    private String priceLow;

    @SerializedName("last")
    private String priceLast;

    @SerializedName("vol_btc")
    private String vol_btc;

    @SerializedName("vol_str")
    private String vol_str;

    public ListTicker(String priceHigh, String priceLow, String priceLast, String vol_btc, String vol_str) {
        this.priceHigh = priceHigh;
        this.priceLow = priceLow;
        this.priceLast = priceLast;
        this.vol_btc = vol_btc;
        this.vol_str = vol_str;
    }

    public String getPriceHigh() {
        return priceHigh;
    }

    public void setPriceHigh(String priceHigh) {
        this.priceHigh = priceHigh;
    }

    public String getPriceLow() {
        return priceLow;
    }

    public void setPriceLow(String priceLow) {
        this.priceLow = priceLow;
    }

    public String getPriceLast() {
        return priceLast;
    }

    public void setPriceLast(String priceLast) {
        this.priceLast = priceLast;
    }

    public String getVol_btc() {
        return vol_btc;
    }

    public void setVol_btc(String vol_btc) {
        this.vol_btc = vol_btc;
    }

    public String getVol_str() {
        return vol_str;
    }

    public void setVol_str(String vol_str) {
        this.vol_str = vol_str;
    }
}
