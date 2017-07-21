package com.example.android.cryptotrade.detailcrypto;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.example.android.cryptotrade.R;
import com.example.android.cryptotrade.utilities.SaveData;
import com.example.android.cryptotrade.utilities.URLAddress;

/**
 * Created by DITHA on 16/07/2017.
 */

public class FragmentTabChart extends Fragment{

    TextView TV_DETAIL_LOW, TV_DETAIL_HIGH;
    ProgressDialog progressDialog;
    WebView myWebView;
    String URL_CHART;
    String lowPrice, highPrice;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_crypto_detail_chart, container, false);

        //getActivity().setTitle("Chart");

        progressDialog = new ProgressDialog(getActivity());
        myWebView = (WebView)v.findViewById(R.id.wvBTC);
        TV_DETAIL_LOW = (TextView)v.findViewById(R.id.tvDetailLow);
        TV_DETAIL_HIGH = (TextView)v.findViewById(R.id.tvDetailHigh);

        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap bitmap) {
                progressDialog.setMessage("Loading....");
                progressDialog.show();
                super.onPageStarted(view, url, bitmap);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                progressDialog .dismiss();
                super.onPageFinished(view, url);
            }
        });
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.getSettings().setBuiltInZoomControls(true);
        myWebView.getSettings().setDisplayZoomControls(true);
        setMyWebView();
        myWebView.loadUrl(URL_CHART);

        return v;
    }

    private void setMyWebView(){
        if(URLAddress.TOOLBAR_NAME.equals("BTC/IDR")){
            URL_CHART = URLAddress.URL_CHART_BTC;
            lowPrice = SaveData.LowPriceBTC;
            highPrice = SaveData.HighPriceBTC;
        } else if(URLAddress.TOOLBAR_NAME.equals("BTS/BTC")){
            URL_CHART = URLAddress.URL_CHART_BTS;
            lowPrice = SaveData.LowPriceBTS;
            highPrice = SaveData.HighPriceBTS;
        } else if(URLAddress.TOOLBAR_NAME.equals("DASH/BTC")){
            URL_CHART = URLAddress.URL_CHART_DASH;
            lowPrice = SaveData.LowPriceDASH;
            highPrice = SaveData.HighPriceDASH;
        } else if(URLAddress.TOOLBAR_NAME.equals("DOGE/BTC")){
            URL_CHART = URLAddress.URL_CHART_DOGE;
            lowPrice = SaveData.LowPriceDOGE;
            highPrice = SaveData.HighPriceDOGE;
        } else if(URLAddress.TOOLBAR_NAME.equals("ETH/BTC")){
            URL_CHART = URLAddress.URL_CHART_ETH;
            lowPrice = SaveData.LowPriceETH;
            highPrice = SaveData.HighPriceETH;
        } else if(URLAddress.TOOLBAR_NAME.equals("LTC/BTC")){
            URL_CHART = URLAddress.URL_CHART_LTC;
            lowPrice = SaveData.LowPriceLTC;
            highPrice = SaveData.HighPriceLTC;
        } else if(URLAddress.TOOLBAR_NAME.equals("NXT/BTC")){
            URL_CHART = URLAddress.URL_CHART_NXT;
            lowPrice = SaveData.LowPriceNXT;
            highPrice = SaveData.HighPriceNXT;
        } else if(URLAddress.TOOLBAR_NAME.equals("STR/BTC")){
            URL_CHART = URLAddress.URL_CHART_STR;
            lowPrice = SaveData.LowPriceSTR;
            highPrice = SaveData.HighPriceSTR;
        } else if(URLAddress.TOOLBAR_NAME.equals("NEM/BTC")){
            URL_CHART = URLAddress.URL_CHART_NEM;
            lowPrice = SaveData.LowPriceNEM;
            highPrice = SaveData.HighPriceNEM;
        } else if(URLAddress.TOOLBAR_NAME.equals("XRP/BTC")){
            URL_CHART = URLAddress.URL_CHART_XRP;
            lowPrice = SaveData.LowPriceXRP;
            highPrice = SaveData.HighPriceXRP;
        }

        TV_DETAIL_LOW.setText(lowPrice);
        TV_DETAIL_HIGH.setText(highPrice);
    }
}
