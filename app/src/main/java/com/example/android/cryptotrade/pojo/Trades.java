package com.example.android.cryptotrade.pojo;

/**
 * Created by DITHA on 16/07/2017.
 */

public class Trades {
    String date;
    String price;
    String amount;
    String type;

    public Trades(String date, String price, String amount, String type) {
        this.date = date;
        this.price = price;
        this.amount = amount;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
