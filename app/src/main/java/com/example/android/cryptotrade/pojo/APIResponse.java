package com.example.android.cryptotrade.pojo;

/**
 * Created by DITHA on 15/07/2017.
 */

public class APIResponse {
    ListTicker ticker;

    public APIResponse(ListTicker ticker) {
        this.ticker = ticker;
    }

    public ListTicker getTicker() {
        return ticker;
    }

    public void setMain(ListTicker main) {
        this.ticker = main;
    }
}
