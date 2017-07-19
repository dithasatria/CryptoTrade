package com.example.android.cryptotrade.pojo;

/**
 * Created by DITHA on 15/07/2017.
 */

public class Ticker {
    String date;
    String price;
    String amount;
    String tid;
    String type;

    public Ticker(String date, String price, String amount, String tid, String type) {
        this.date = date;
        this.price = price;
        this.amount = amount;
        this.tid = tid;
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
