package com.example.android.cryptotrade.pojo;

/**
 * Created by DITHA on 21/07/2017.
 */

public class Alert {
    private int id;
    private String cryptoName;
    private String price;
    private String status;

    public Alert(){

    }

    public Alert(int id, String cryptoName, String price, String status) {
        super();
        this.id = id;
        this.cryptoName = cryptoName;
        this.price = price;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCryptoName() {
        return cryptoName;
    }

    public void setCryptoName(String cryptoName) {
        this.cryptoName = cryptoName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
