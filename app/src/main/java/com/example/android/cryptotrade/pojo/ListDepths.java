package com.example.android.cryptotrade.pojo;

import java.util.List;

/**
 * Created by DITHA on 16/07/2017.
 */

public class ListDepths {
    List<List<String>> buy;
    List<List<String>> sell;

    public ListDepths(List<List<String>> buy, List<List<String>> sell) {
        this.buy = buy;
        this.sell = sell;
    }

    public List<List<String>> getBuy() {
        return buy;
    }

    public void setBuy(List<List<String>> buy) {
        this.buy = buy;
    }

    public List<List<String>> getSell() {
        return sell;
    }

    public void setSell(List<List<String>> sell) {
        this.sell = sell;
    }
}