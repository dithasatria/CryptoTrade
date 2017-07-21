package com.example.android.cryptotrade.fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.cryptotrade.R;
import com.example.android.cryptotrade.detailcrypto.CryptoDetail;
import com.example.android.cryptotrade.pojo.APIResponse;
import com.example.android.cryptotrade.utilities.CurrencyConverter;
import com.example.android.cryptotrade.utilities.PrefManager;
import com.example.android.cryptotrade.utilities.SaveData;
import com.example.android.cryptotrade.utilities.URLAddress;
import com.google.gson.Gson;

import java.text.DecimalFormat;

/**
 * A simple {@link Fragment} subclass.
 */
public class MarketFragment extends Fragment implements View.OnClickListener{

    private TextView TV_PRICE, TV_HIGHPRICE, TV_LOWPRICE, TV_VOL;
    private TextView TV_PRICE_STR, TV_CHANGE_STR, TV_PERCENT_STR;
    private TextView TV_PRICE_BTS, TV_CHANGE_BTS, TV_PERCENT_BTS;
    private TextView TV_PRICE_DASH, TV_CHANGE_DASH, TV_PERCENT_DASH;
    private TextView TV_PRICE_DOGE, TV_CHANGE_DOGE, TV_PERCENT_DOGE;
    private TextView TV_PRICE_ETH, TV_CHANGE_ETH, TV_PERCENT_ETH;
    private TextView TV_PRICE_LTC, TV_CHANGE_LTC, TV_PERCENT_LTC;
    private TextView TV_PRICE_NXT, TV_CHANGE_NXT, TV_PERCENT_NXT;
    private TextView TV_PRICE_NEM, TV_CHANGE_NEM, TV_PERCENT_NEM;
    private TextView TV_PRICE_XRP, TV_CHANGE_XRP, TV_PERCENT_XRP;
    private ProgressDialog progressDialog;

    private LinearLayout layoutBTC, layoutBTS, layoutDASH, layoutDOGE, layoutETH, layoutLTC, layoutNXT, layoutSTR, layoutNEM, layoutXRP;
    Handler mHandler;
    Gson gson = new Gson();
    PrefManager pref = new PrefManager();
    Long second;

    private DecimalFormat format = new DecimalFormat("0.00000000");

    public MarketFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_market, container, false);

        this.mHandler = new Handler();
        m_Runnable.run();

        progressDialog = new ProgressDialog(getActivity());

        TV_PRICE = (TextView)v.findViewById(R.id.tvPrices);
        TV_HIGHPRICE = (TextView)v.findViewById(R.id.tvHigh);
        TV_LOWPRICE = (TextView)v.findViewById(R.id.tvLow);
        TV_VOL = (TextView)v.findViewById(R.id.tvVol);

        TV_PRICE_STR = (TextView) v.findViewById(R.id.tvLastPriceSTR);
        TV_CHANGE_STR = (TextView)v.findViewById(R.id.tvChangeSTR);
        TV_PERCENT_BTS = (TextView)v.findViewById(R.id.tvBTSPercent);

        TV_PRICE_BTS = (TextView)v.findViewById(R.id.tvLastPriceBTS);
        TV_CHANGE_BTS = (TextView)v.findViewById(R.id.tvChangeBTS);

        TV_PRICE_DASH = (TextView)v.findViewById(R.id.tvLastPriceDASH);
        TV_CHANGE_DASH = (TextView)v.findViewById(R.id.tvChangeDASH);

        TV_PRICE_DOGE = (TextView)v.findViewById(R.id.tvLastPriceDOGE);
        TV_CHANGE_DOGE = (TextView)v.findViewById(R.id.tvChangeDOGE);

        TV_PRICE_ETH = (TextView)v.findViewById(R.id.tvLastPriceETH);
        TV_CHANGE_ETH = (TextView)v.findViewById(R.id.tvChangeETH);

        TV_PRICE_LTC = (TextView)v.findViewById(R.id.tvLastPriceLTC);
        TV_CHANGE_LTC = (TextView)v.findViewById(R.id.tvChangeLTC);

        TV_PRICE_NXT = (TextView)v.findViewById(R.id.tvLastPriceNXT);
        TV_CHANGE_NXT = (TextView)v.findViewById(R.id.tvChangeNXT);

        TV_PRICE_NEM = (TextView)v.findViewById(R.id.tvLastPriceNEM);
        TV_CHANGE_NEM = (TextView)v.findViewById(R.id.tvChangeNEM);

        TV_PRICE_XRP = (TextView)v.findViewById(R.id.tvLastPriceXRP);
        TV_CHANGE_XRP = (TextView)v.findViewById(R.id.tvChangeXRP);

        layoutBTC = (LinearLayout)v.findViewById(R.id.layoutBTC);
        layoutBTS = (LinearLayout)v.findViewById(R.id.layoutBTS);
        layoutDASH = (LinearLayout)v.findViewById(R.id.layoutDASH);
        layoutDOGE = (LinearLayout)v.findViewById(R.id.layoutDOGE);
        layoutETH = (LinearLayout)v.findViewById(R.id.layoutETH);
        layoutLTC = (LinearLayout)v.findViewById(R.id.layoutLTC);
        layoutNXT = (LinearLayout)v.findViewById(R.id.layoutNXT);
        layoutSTR = (LinearLayout)v.findViewById(R.id.layoutSTR);
        layoutNEM = (LinearLayout)v.findViewById(R.id.layoutNEM);
        layoutXRP = (LinearLayout)v.findViewById(R.id.layoutXRP);

        layoutBTC.setOnClickListener(this);
        layoutBTS.setOnClickListener(this);
        layoutDASH.setOnClickListener(this);
        layoutDOGE.setOnClickListener(this);
        layoutETH.setOnClickListener(this);
        layoutLTC.setOnClickListener(this);
        layoutNXT.setOnClickListener(this);
        layoutSTR.setOnClickListener(this);
        layoutNEM.setOnClickListener(this);
        layoutXRP.setOnClickListener(this);

        getActivity().setTitle("Market");

        LoadData();

        return v;
    }

    private void LoadData(){
        LoadDataBTC();
        LoadDataBTS();
        LoadDataDASH();
        LoadDataDOGE();
        LoadDataETH();
        LoadDataLTC();
        LoadDataNXT();
        LoadDataSTR();
        LoadDataNEM();
        LoadDataXRP();
    }

    private void LoadDataBTC() {
        RequestQueue queue = Volley.newRequestQueue(getContext());

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URLAddress.URL_BTC,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            APIResponse resp = gson.fromJson(response, APIResponse.class);

                            Double lastPrice = Double.parseDouble(resp.getTicker().getPriceLast());
                            Double lowPrice = Double.parseDouble(resp.getTicker().getPriceLow());
                            Double highPrice = Double.parseDouble(resp.getTicker().getPriceHigh());
                            Double vol_btc = Double.parseDouble(resp.getTicker().getVol_btc());

                            CurrencyConverter currencyFormat = new CurrencyConverter();
                            TV_PRICE.setText(String.valueOf(currencyFormat.kursIndonesia.format(lastPrice)));
                            TV_HIGHPRICE.setText(String.valueOf(currencyFormat.kursIndonesia.format(highPrice)));
                            TV_LOWPRICE.setText(String.valueOf(currencyFormat.kursIndonesia.format(lowPrice)));
                            TV_VOL.setText(String.valueOf(vol_btc + " BTC"));

                            SaveData.PriceBTC = currencyFormat.kursIndonesia.format(lastPrice).toString();
                            SaveData.LowPriceBTC = currencyFormat.kursIndonesia.format(lowPrice).toString();
                            SaveData.HighPriceBTC = currencyFormat.kursIndonesia.format(highPrice).toString();

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //progressDialog.dismiss();
                        error.printStackTrace();
                    }
                });

        // Adding request to request queue
        queue.add(stringRequest);

        //ProggressDialogShow();
    }

    private void LoadDataBTS() {
        RequestQueue queue = Volley.newRequestQueue(getContext());

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URLAddress.URL_BTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            APIResponse resp = gson.fromJson(response, APIResponse.class);

                            Double lastPrice = Double.parseDouble(resp.getTicker().getPriceLast());
                            Double lowPrice = Double.parseDouble(resp.getTicker().getPriceLow());
                            Double highPrice = Double.parseDouble(resp.getTicker().getPriceHigh());

                            TV_PRICE_BTS.setText(format.format(lastPrice) + " BTC");

                            Double hasil = lastPrice - lowPrice;
                            //Double hasilPercent = (lastPrice - lowPrice)/100;

                            TV_CHANGE_BTS.setText(format.format(hasil) +"");

                            SaveData.PriceBTS = resp.getTicker().getPriceLast();
                            SaveData.LowPriceBTS = format.format(lowPrice) + " BTC";
                            SaveData.HighPriceBTS = format.format(highPrice) + " BTC";

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        // Adding request to request queue
        queue.add(stringRequest);
    }

    private void LoadDataDASH() {
        RequestQueue queue = Volley.newRequestQueue(getContext());

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URLAddress.URL_DASH,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            APIResponse resp = gson.fromJson(response, APIResponse.class);

                            Double lastPrice = Double.parseDouble(resp.getTicker().getPriceLast());
                            Double lowPrice = Double.parseDouble(resp.getTicker().getPriceLow());
                            Double highPrice = Double.parseDouble(resp.getTicker().getPriceHigh());

                            TV_PRICE_DASH.setText(format.format(lastPrice) + " BTC");
                            Double hasil = lastPrice - lowPrice;

                            TV_CHANGE_DASH.setText(format.format(hasil) +"");

                            SaveData.PriceDASH = format.format(lastPrice);
                            SaveData.LowPriceDASH = format.format(lowPrice) + " BTC";
                            SaveData.HighPriceDASH = format.format(highPrice) + " BTC";

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        // Adding request to request queue
        queue.add(stringRequest);
    }

    private void LoadDataDOGE() {
        RequestQueue queue = Volley.newRequestQueue(getContext());

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URLAddress.URL_DOGE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            APIResponse resp = gson.fromJson(response, APIResponse.class);

                            Double lastPrice = Double.parseDouble(resp.getTicker().getPriceLast());
                            Double lowPrice = Double.parseDouble(resp.getTicker().getPriceLow());
                            Double highPrice = Double.parseDouble(resp.getTicker().getPriceHigh());

                            TV_PRICE_DOGE.setText(format.format(lastPrice) + " BTC");
                            Double hasil = lastPrice - lowPrice;

                            TV_CHANGE_DOGE.setText(format.format(hasil) +"");

                            SaveData.PriceDOGE = format.format(lastPrice);
                            SaveData.LowPriceDOGE = format.format(lowPrice) + " BTC";
                            SaveData.HighPriceDOGE = format.format(highPrice) + " BTC";
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        // Adding request to request queue
        queue.add(stringRequest);
    }

    private void LoadDataSTR() {
        RequestQueue queue = Volley.newRequestQueue(getContext());

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URLAddress.URL_STR,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            APIResponse resp = gson.fromJson(response, APIResponse.class);

                            Double lastPrice = Double.parseDouble(resp.getTicker().getPriceLast());
                            Double lowPrice = Double.parseDouble(resp.getTicker().getPriceLow());
                            Double highPrice = Double.parseDouble(resp.getTicker().getPriceHigh());

                            TV_PRICE_STR.setText(format.format(lastPrice) + " BTC");

                            Double hasil = lastPrice - lowPrice;

                            TV_CHANGE_STR.setText(format.format(hasil) +"");

                            SaveData.PriceSTR = format.format(lastPrice);
                            SaveData.LowPriceSTR = format.format(lowPrice) + " BTC";
                            SaveData.HighPriceSTR = format.format(highPrice) + " BTC";

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        // Adding request to request queue
        queue.add(stringRequest);
    }

    private void LoadDataETH() {
        RequestQueue queue = Volley.newRequestQueue(getContext());

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URLAddress.URL_ETH,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            APIResponse resp = gson.fromJson(response, APIResponse.class);

                            Double lastPrice = Double.parseDouble(resp.getTicker().getPriceLast());
                            Double lowPrice = Double.parseDouble(resp.getTicker().getPriceLow());
                            Double highPrice = Double.parseDouble(resp.getTicker().getPriceHigh());

                            TV_PRICE_ETH.setText(format.format(lastPrice) + " BTC");

                            Double hasil = lastPrice - lowPrice;

                            TV_CHANGE_ETH.setText(format.format(hasil) +"");

                            SaveData.PriceETH = format.format(lastPrice);
                            SaveData.LowPriceETH = format.format(lowPrice) + " BTC";
                            SaveData.HighPriceETH = format.format(highPrice) + " BTC";
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        // Adding request to request queue
        queue.add(stringRequest);
    }

    private void LoadDataLTC() {
        RequestQueue queue = Volley.newRequestQueue(getContext());

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URLAddress.URL_LTC,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            APIResponse resp = gson.fromJson(response, APIResponse.class);

                            Double lastPrice = Double.parseDouble(resp.getTicker().getPriceLast());
                            Double lowPrice = Double.parseDouble(resp.getTicker().getPriceLow());
                            Double highPrice = Double.parseDouble(resp.getTicker().getPriceHigh());

                            Double hasil = lastPrice - lowPrice;

                            TV_CHANGE_LTC.setText(format.format(hasil) +"");

                            TV_PRICE_LTC.setText(format.format(lastPrice) + " BTC");

                            SaveData.PriceLTC = format.format(lastPrice);
                            SaveData.LowPriceLTC = format.format(lowPrice) + " BTC";
                            SaveData.HighPriceLTC = format.format(highPrice) + " BTC";
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        // Adding request to request queue
        queue.add(stringRequest);
    }

    private void LoadDataNXT() {
        RequestQueue queue = Volley.newRequestQueue(getContext());

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URLAddress.URL_NXT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            APIResponse resp = gson.fromJson(response, APIResponse.class);

                            Double lastPrice = Double.parseDouble(resp.getTicker().getPriceLast());
                            Double lowPrice = Double.parseDouble(resp.getTicker().getPriceLow());
                            Double highPrice = Double.parseDouble(resp.getTicker().getPriceHigh());

                            Double hasil = lastPrice - lowPrice;

                            TV_CHANGE_NXT.setText(format.format(hasil) +"");

                            TV_PRICE_NXT.setText(format.format(lastPrice) + " BTC");

                            SaveData.PriceNXT = format.format(lastPrice);
                            SaveData.LowPriceNXT = format.format(lowPrice) + " BTC";
                            SaveData.HighPriceNXT = format.format(highPrice) + " BTC";
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        // Adding request to request queue
        queue.add(stringRequest);
    }

    private void LoadDataNEM() {
        RequestQueue queue = Volley.newRequestQueue(getContext());

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URLAddress.URL_NEM,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            APIResponse resp = gson.fromJson(response, APIResponse.class);

                            Double lastPrice = Double.parseDouble(resp.getTicker().getPriceLast());
                            Double lowPrice = Double.parseDouble(resp.getTicker().getPriceLow());
                            Double highPrice = Double.parseDouble(resp.getTicker().getPriceHigh());

                            Double hasil = lastPrice - lowPrice;

                            TV_CHANGE_NEM.setText(format.format(hasil) +"");

                            TV_PRICE_NEM.setText(format.format(lastPrice) + " BTC");

                            SaveData.PriceNEM = format.format(lastPrice);
                            SaveData.LowPriceNEM = format.format(lowPrice) + " BTC";
                            SaveData.HighPriceNEM = format.format(highPrice) + " BTC";
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        // Adding request to request queue
        queue.add(stringRequest);
    }

    private void LoadDataXRP() {
        RequestQueue queue = Volley.newRequestQueue(getContext());

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URLAddress.URL_XRP,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            APIResponse resp = gson.fromJson(response, APIResponse.class);

                            Double lastPrice = Double.parseDouble(resp.getTicker().getPriceLast());
                            Double lowPrice = Double.parseDouble(resp.getTicker().getPriceLow());
                            Double highPrice = Double.parseDouble(resp.getTicker().getPriceHigh());

                            Double hasil = lastPrice - lowPrice;

                            TV_CHANGE_XRP.setText(format.format(hasil) +"");

                            TV_PRICE_XRP.setText(format.format(lastPrice) + " BTC");

                            SaveData.PriceXRP = format.format(lastPrice);
                            SaveData.LowPriceXRP = format.format(lowPrice) + " BTC";
                            SaveData.HighPriceXRP = format.format(highPrice) + " BTC";
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        // Adding request to request queue
        queue.add(stringRequest);
    }

    @Override
    public void onClick(View v) {
        if(SaveData.PriceBTC != null
                && SaveData.PriceBTS != null
                && SaveData.PriceDASH != null
                && SaveData.PriceDOGE != null
                && SaveData.PriceETH != null
                && SaveData.PriceLTC != null
                && SaveData.PriceNXT != null
                && SaveData.PriceSTR != null
                && SaveData.PriceNEM != null
                && SaveData.PriceXRP != null) {
            if(v == layoutBTC){
                URLAddress.TOOLBAR_NAME = "BTC/IDR";
                URLAddress.TRADES = "btc_idr";
                startActivity(new Intent(getContext(), CryptoDetail.class));
            }
            if (v == layoutBTS) {
                URLAddress.TOOLBAR_NAME = "BTS/BTC";
                URLAddress.TRADES = "bts_btc";
                startActivity(new Intent(getContext(), CryptoDetail.class));
            }
            if (v == layoutDASH) {
                URLAddress.TOOLBAR_NAME = "DASH/BTC";
                URLAddress.TRADES = "drk_btc";
                startActivity(new Intent(getContext(), CryptoDetail.class));
            }
            if (v == layoutDOGE) {
                URLAddress.TOOLBAR_NAME = "DOGE/BTC";
                URLAddress.TRADES = "doge_btc";
                startActivity(new Intent(getContext(), CryptoDetail.class));
            }
            if (v == layoutETH) {
                URLAddress.TOOLBAR_NAME = "ETH/BTC";
                URLAddress.TRADES = "eth_btc";
                startActivity(new Intent(getContext(), CryptoDetail.class));
            }
            if (v == layoutLTC) {
                URLAddress.TOOLBAR_NAME = "LTC/BTC";
                URLAddress.TRADES = "ltc_btc";
                startActivity(new Intent(getContext(), CryptoDetail.class));
            }
            if (v == layoutNXT) {
                URLAddress.TOOLBAR_NAME = "NXT/BTC";
                URLAddress.TRADES = "nxt_btc";
                startActivity(new Intent(getContext(), CryptoDetail.class));
            }
            if (v == layoutSTR) {
                URLAddress.TOOLBAR_NAME = "STR/BTC";
                URLAddress.TRADES = "str_btc";
                startActivity(new Intent(getContext(), CryptoDetail.class));
            }
            if (v == layoutNEM) {
                URLAddress.TOOLBAR_NAME = "NEM/BTC";
                URLAddress.TRADES = "nem_btc";
                startActivity(new Intent(getContext(), CryptoDetail.class));
            }
            if (v == layoutXRP) {
                URLAddress.TOOLBAR_NAME = "XRP/BTC";
                URLAddress.TRADES = "xrp_btc";
                startActivity(new Intent(getContext(), CryptoDetail.class));
            }
        }
    }

    private final Runnable m_Runnable = new Runnable()
    {
        public void run()
        {
            try {
                LoadData();
                second = Long.parseLong(pref.getString(getActivity(), "refreshRate"));
                mHandler.postDelayed(m_Runnable, second);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    };
}
